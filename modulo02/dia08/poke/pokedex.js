
function rodarPrograma() {

  const min = 1
  const max = 802

  localStorage.setItem("idGerados", JSON.stringify([]));

  const $inputCode = document.getElementById("inputCode")

  const url = 'https://pokeapi.co/api/v2/pokemon'

  const pokeApi = new PokeApi( url )

  $inputCode.onblur = function() {
    const code = $inputCode.value
    if(code != "" && verificarPokemonAtual( code ) && $inputCode.checkValidity()){
      pokeApi.buscar( code )
        .then( res => res.json() )
        .then( dadosJson => {
          // criar objeto pokémon e renderiza-lo na tela
          const pokemon = new Pokemon( dadosJson )
          new Impressora( pokemon )
        } )
    }
  }

  const $btnRandom = document.getElementById("btnRandom")

  $btnRandom.onclick = function() {
    const random = randomNumberInterval(min, max)
    if(random != null){
      $inputCode.value = random
      $inputCode.onblur.call()
    }
  }

}

function randomNumberInterval(min,max){
  var storedId = JSON.parse(localStorage.getItem("idGerados"))
  const random = Math.round( (Math.random() * max) + min)
  if(storedId[random]){
    return null
  }
  storedId[random] = random
  localStorage.setItem("idGerados", JSON.stringify(storedId));
  return random
}

function verificarPokemonAtual( code ){
  const pokemonLocal = document.getElementById( 'id' ).innerText
  return !(code === pokemonLocal ? true : false)
}

rodarPrograma()
