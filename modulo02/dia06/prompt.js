const meuH2 = document.getElementById( 'tituloPagina' )

function perguntarNome() {
  const nome = prompt( 'Qual seu nome?' )
  meuH2.innerText = nome
  localStorage.nome = nome
}

function renderizaNomeArmazenadoNaTela() {
  meuH2.innerText = localStorage.nome
}

function rodarPrograma() {
  const nomeArmazenado = localStorage.nome && localStorage.nome.length > 0
  if ( nomeArmazenado ) {
    renderizaNomeArmazenadoNaTela()
  } else {
    perguntarNome()
  }

  const btnLimparDados = document.getElementById( 'btnLimparDados' )

  const temQueDesabilitar = localStorage.qtdCliques >= 5
  if ( temQueDesabilitar ) {
    btnLimparDados.disabled = true
  }

  btnLimparDados.onclick = function () {
    localStorage.removeItem( 'nome' )
    localStorage.qtdCliques++;
    if(localStorage.qtdCliques >= 5) {
      btnLimparDados.disabled = true
    }
  }

  if( !localStorage.qtdCliques ) {
    localStorage.qtdCliques = 0
  }
}

rodarPrograma()


//webdriverjs, Selenium, Protractor

//meuH2.innerText = 'Carregando...'

//localStorage['nome'] = nome
//localStorage.setItem( 'nome', nome )
