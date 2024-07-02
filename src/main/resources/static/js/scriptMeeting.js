document.addEventListener('DOMContentLoaded', function() {
    fetchCategorias();

    const categoriasContainer = document.getElementById('categoriasList');
    categoriasContainer.addEventListener('click', function(event) {
        if (event.target.tagName === 'LI') {
            const categoriaId = event.target.dataset.id;
            fetchPlatillosPorCategoria(categoriaId);
        }
    });

    const platillosContainer = document.getElementById('platillosList');
    platillosContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('addPlatilloBtn')) {
            const platillo = {
                id: event.target.dataset.id,
                nombre: event.target.dataset.nombre,
                descripcion: event.target.dataset.descripcion,
                precio: parseFloat(event.target.dataset.precio)
            };
            abrirModalPlatillo(platillo);
        }
    });

    document.querySelector('.closeBtn').addEventListener('click', function() {
        document.getElementById('platilloModal').style.display = 'none';
    });

    document.getElementById('confirmAdd').addEventListener('click', function() {
        const platillo = {
            id: this.dataset.id,
            nombre: this.dataset.nombre,
            descripcion: this.dataset.descripcion,
            precio: parseFloat(this.dataset.precio)
        };
        agregarPlatilloAOrden(platillo);
        document.getElementById('platilloModal').style.display = 'none';
    });

    document.getElementById('comprar').addEventListener('click', function() {
        enviarOrden();
    });
});

function fetchCategorias() {
    fetch('/api/menu/categorias')
        .then(response => response.json())
        .then(categorias => {
            const categoriasList = document.getElementById('categoriasList');
            categoriasList.innerHTML = '';
            categorias.forEach(categoria => {
                const li = document.createElement('li');
                li.textContent = categoria.nombre;
                li.dataset.id = categoria.id;
                categoriasList.appendChild(li);
            });
        })
        .catch(error => console.error('Error al cargar categorías:', error));
}

function fetchPlatillosPorCategoria(categoriaId) {
    fetch(`/api/menu/platillos/${categoriaId}`)
        .then(response => response.json())
        .then(platillos => {
            document.getElementById('welcomeMessage').style.display = 'none';
            document.getElementById('menuTable').style.display = 'table';

            const platillosList = document.getElementById('platillosList');
            platillosList.innerHTML = '';
            platillos.forEach(platillo => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${platillo.nombre}<br><small>${platillo.descripcion}</small></td><td>€${platillo.precio.toFixed(2)}</td><td><button class="addPlatilloBtn" data-id="${platillo.id}" data-nombre="${platillo.nombre}" data-precio="${platillo.precio}" data-descripcion="${platillo.descripcion}">+</button></td>`;
                platillosList.appendChild(tr);
            });
        })
        .catch(error => console.error('Error al cargar platillos:', error));
}

function abrirModalPlatillo(platillo) {
    const modal = document.getElementById('platilloModal');
    document.getElementById('modalTitle').textContent = platillo.nombre;
    document.getElementById('modalDescription').textContent = platillo.descripcion || "No disponible";
    document.getElementById('modalPrice').textContent = `Precio: €${platillo.precio.toFixed(2)}`;
    document.getElementById('modalQuantity').value = 1; // Reset quantity input
    document.getElementById('sizeSelect').value = "Regular"; // Reset size selection

    document.getElementById('confirmAdd').dataset.id = platillo.id;
    document.getElementById('confirmAdd').dataset.nombre = platillo.nombre;
    document.getElementById('confirmAdd').dataset.descripcion = platillo.descripcion;
    document.getElementById('confirmAdd').dataset.precio = platillo.precio;

    modal.style.display = 'block';
}

function agregarPlatilloAOrden(platillo) {
    const cantidad = parseInt(document.getElementById('modalQuantity').value);
    const size = document.getElementById('sizeSelect').value;
    let precioFinal = platillo.precio;

    if (size === "Grande") {
        precioFinal *= 1.2; // 20% extra for grande size
    }

    const ordenList = document.getElementById('ordenList');
    const item = document.createElement('li');
    item.textContent = `${cantidad} x ${platillo.nombre} - Tamaño: ${size} - Total: €${(precioFinal * cantidad).toFixed(2)}`;
    ordenList.appendChild(item);
    actualizarTotal(precioFinal * cantidad);
}

function actualizarTotal(monto) {
    const totalElement = document.getElementById('total');
    let totalActual = parseFloat(totalElement.textContent) || 0;
    totalActual += monto;
    totalElement.textContent = totalActual.toFixed(2);
}

function enviarOrden() {
    const orden = {
        items: obtenerItemsOrden(),
        total: parseFloat(document.getElementById('total').textContent)
    };

    fetch('/api/menu/orden', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orden)
    })
        .then(response => response.json())
        .then(data => {
            mostrarModalConfirmacion(data.ordenId);
        })
        .catch(error => console.error('Error al enviar la orden:', error));
}

function obtenerItemsOrden() {
    const items = [];
    const itemList = document.querySelectorAll('#ordenList li');
    itemList.forEach(item => {
        const text = item.textContent.split(' - ');
        items.push({
            nombrePlatillo: text[0].split(' x ')[1],
            size: text[1].split(': ')[1],
            cantidad: parseInt(text[0].split(' x ')[0]),
            precio: parseFloat(text[2].split(': ')[1].replace('€', ''))
        });
    });
    return items;
}

function mostrarModalConfirmacion(ordenId) {
    const modal = document.getElementById('confirmationModal');
    document.querySelector('#confirmationModal p:nth-child(2)').textContent = `SU ORDEN ES LA #${ordenId}`;
    modal.style.display = 'block';
}

function cerrarModal() {
    document.getElementById('confirmationModal').style.display = 'none';
}
