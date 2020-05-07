// https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/Date

const h1 = document.getElementById( 'horario' )

class Relogio{
  constructor( elemento ) {
    this.elementoHtml = elemento,
    this.idRelogio,
    this.iniciar()
  }

  iniciar() {
    setTextoNoElemento(this.elementoHtml , moment().format('LTS'))
    this.reiniciar()
  }

  parar() {
      clearInterval(this.idRelogio)
  }

  reiniciar() {
    this.idRelogio = criarIntervalo(this.elementoHtml)
  }
}

function criarIntervalo(elemento){
  return setInterval(function(){
    setTextoNoElemento(elemento, moment().format('LTS'))
  }, 1000)
}

function setTextoNoElemento( elemento, texto ){
  elemento.innerText = texto
}

function rodarPrograma () {

  const relogio = new Relogio(h1)
  
  const btnPararRelogio = document.getElementById('btnPararRelogio')
  
  btnPararRelogio.onclick = function() {
    relogio.parar();
    trocarBotoes();
  }

  const btnReiniciarRelogio = document.getElementById('btnReiniciarRelogio')
  btnReiniciarRelogio.disabled = true

  btnReiniciarRelogio.onclick = function() {
    relogio.reiniciar()
    trocarBotoes()
  }
}

function trocarBotoes(){
  btnReiniciarRelogio.disabled = !btnReiniciarRelogio.disabled
  btnPararRelogio.disabled = !btnPararRelogio.disabled
}

rodarPrograma()
