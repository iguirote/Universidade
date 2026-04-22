const botao = document.getElementById("btnMostrar")
const tabela = document.getElementById("tabela")

function mostrarTudo() {
    botao.style.display = "none"
    tabela.style.display = "table"
}

botao.addEventListener("click", mostrarTudo)

const form = document.getElementById("meuFormulario");
const nome = document.getElementById("nome");
const email = document.getElementById("email");

form.addEventListener("submit", function(event) {
    event.preventDefault();

    if (nome.value.trim() === "") {
        alert("O campo Nome não pode estar vazio!");
        return;
    }

    if (!email.value.includes("@")) {
        alert("Digite um email válido!");
        return;
    }

    alert(`Mensagem de ${nome.value} enviada com sucesso!`);

    nome.value = "";
    email.value = "";
});