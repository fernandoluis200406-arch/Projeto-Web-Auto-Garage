// ==========================================
// AUTO GARAGE
// ==========================================

const carros = {

    audi: {
        id: "audi",
        nome: "Audi R8",
        categoria: "ESPORTIVO",
        preco: 1290000,
        descricao:
            "Um dos esportivos mais marcantes da Audi, combinando desempenho, tecnologia e um design agressivo.",
        especificacoes:
            "Motor V10 • 5.2 litros • Automático • 2023",
        imagem:
            "https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?auto=format&fit=crop&w=1400&q=85"
    },

    bugatti: {
        id: "bugatti",
        nome: "Bugatti Chiron",
        categoria: "HYPERCAR",
        preco: 18500000,
        descricao:
            "Um hypercar de altíssimo desempenho criado para entregar uma experiência única de velocidade e exclusividade.",
        especificacoes:
            "Motor W16 • 8.0 litros • Automático • 2022",
        imagem:
            "https://commons.wikimedia.org/wiki/Special:FilePath/Bugatti_Chiron_2017.jpg"
    }

};


// ==========================================
// FORMATAÇÃO
// ==========================================

function formatarPreco(valor) {

    return valor.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    );
}


// ==========================================
// CARRINHO
// ==========================================

function obterCarrinho() {

    return JSON.parse(
        localStorage.getItem("autoGarageCarrinho")
    ) || [];
}


function salvarCarrinho(carrinho) {

    localStorage.setItem(
        "autoGarageCarrinho",
        JSON.stringify(carrinho)
    );
}


function atualizarContador() {

    const elemento =
        document.getElementById("cart-count");

    if (!elemento) {
        return;
    }

    elemento.textContent =
        obterCarrinho().length;
}


function adicionarCarrinho(id) {

    const carro = carros[id];

    if (!carro) {
        return;
    }

    let carrinho = obterCarrinho();

    const jaExiste =
        carrinho.some(item => item.id === id);

    if (!jaExiste) {

        carrinho.push(carro);

        salvarCarrinho(carrinho);
    }

    window.location.href = "carrinho";
}


function removerCarrinho(id) {

    let carrinho = obterCarrinho();

    carrinho =
        carrinho.filter(item => item.id !== id);

    salvarCarrinho(carrinho);

    carregarCarrinho();

    atualizarContador();
}


// ==========================================
// DETALHES
// ==========================================

function carregarDetalhes() {

    const container =
        document.getElementById("car-details");

    if (!container) {
        return;
    }

    const params =
        new URLSearchParams(
            window.location.search
        );

    const id =
        params.get("carro") || "audi";

    const carro = carros[id];

    if (!carro) {
        container.innerHTML = `
            <div class="empty-card">
                <h2>Carro não encontrado</h2>
                <a href="home" class="primary-button">
                    Voltar
                </a>
            </div>
        `;

        return;
    }

    container.innerHTML = `

        <div class="details-card">

            <div class="details-image">

                <img
                    src="${carro.imagem}"
                    alt="${carro.nome}">

            </div>

            <div class="details-content">

                <span class="eyebrow">
                    ${carro.categoria}
                </span>

                <h1>
                    ${carro.nome}
                </h1>

                <p class="details-description">
                    ${carro.descricao}
                </p>

                <div class="spec-box">

                    <span>
                        ESPECIFICAÇÕES
                    </span>

                    <strong>
                        ${carro.especificacoes}
                    </strong>

                </div>

                <div class="details-price">

                    <span>
                        INVESTIMENTO
                    </span>

                    <strong>
                        ${formatarPreco(carro.preco)}
                    </strong>

                </div>

                <button
                    class="primary-button"
                    onclick="adicionarCarrinho('${carro.id}')">

                    Adicionar ao carrinho

                </button>

            </div>

        </div>
    `;
}


// ==========================================
// CARRINHO
// ==========================================

function carregarCarrinho() {

    const container =
        document.getElementById("cart-items");

    const summary =
        document.getElementById("cart-summary");

    if (!container) {
        return;
    }

    const carrinho = obterCarrinho();

    if (carrinho.length === 0) {

        container.innerHTML = `

            <div class="empty-card">

                <div class="empty-icon">
                    🛒
                </div>

                <h2>
                    Seu carrinho está vazio
                </h2>

                <p>
                    Escolha um veículo para continuar.
                </p>

                <a href="home"
                   class="primary-button">

                    Ver carros

                </a>

            </div>

        `;

        if (summary) {
            summary.innerHTML = "";
        }

        return;
    }

    container.innerHTML =
        carrinho.map(carro => `

            <div class="cart-item">

                <img
                    src="${carro.imagem}"
                    alt="${carro.nome}">

                <div class="cart-item-info">

                    <span>
                        ${carro.categoria}
                    </span>

                    <h3>
                        ${carro.nome}
                    </h3>

                    <strong>
                        ${formatarPreco(carro.preco)}
                    </strong>

                </div>

                <button
                    class="remove-button"
                    onclick="removerCarrinho('${carro.id}')">

                    Remover

                </button>

            </div>

        `).join("");


    const total =
        carrinho.reduce(
            (soma, carro) =>
                soma + carro.preco,
            0
        );


    if (summary) {

        summary.innerHTML = `

            <div>

                <span>
                    TOTAL
                </span>

                <strong>
                    ${formatarPreco(total)}
                </strong>

            </div>

            <button
                class="primary-button"
                onclick="finalizarCompra()">

                Finalizar compra

            </button>

        `;
    }
}


// ==========================================
// FINALIZAR
// ==========================================

function finalizarCompra() {

    const carrinho = obterCarrinho();

    if (carrinho.length === 0) {
        return;
    }

    localStorage.setItem(
        "autoGarageCompra",
        JSON.stringify(carrinho[0])
    );

    localStorage.removeItem(
        "autoGarageCarrinho"
    );

    window.location.href = "compra";
}


// ==========================================
// COMPRA
// ==========================================

function carregarCompra() {

    const container =
        document.getElementById("purchase-car");

    if (!container) {
        return;
    }

    const dados =
        localStorage.getItem(
            "autoGarageCompra"
        );

    if (!dados) {

        container.innerHTML = `
            <p>
                Nenhum veículo selecionado.
            </p>
        `;

        return;
    }

    const carro =
        JSON.parse(dados);

    container.innerHTML = `

        <img
            src="${carro.imagem}"
            alt="${carro.nome}">

        <div>

            <span>
                VEÍCULO
            </span>

            <strong>
                ${carro.nome}
            </strong>

            <p>
                ${formatarPreco(carro.preco)}
            </p>

        </div>

    `;
}


// ==========================================
// INICIALIZAÇÃO
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        atualizarContador();

        carregarDetalhes();

        carregarCarrinho();

        carregarCompra();

    }
);