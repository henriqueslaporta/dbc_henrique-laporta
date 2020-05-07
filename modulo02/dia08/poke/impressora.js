  
class Impressora{
  constructor( pokemon ){
    this.pokemon = pokemon,
    this.imprimir()
  }

  imprimir(){
    const $dadosPokemon = document.getElementById( 'dadosPokemon' )
    const $h1 = $dadosPokemon.querySelector( 'h1' )
    const $img = $dadosPokemon.querySelector( '#thumb' )
    const $id = document.getElementById( 'id' )
    const $altura = document.getElementById( 'altura' )
    const $peso = document.getElementById( 'peso' )

    $h1.innerText = this.pokemon.nome
    $img.src = this.pokemon.img
    $id.innerText = this.pokemon.id
    $altura.innerText = `${this.pokemon.altura} cm`
    $peso.innerText = `${this.pokemon.peso} kg`

    this.limparListas()

    this.imprimirTipos()

    this.imprimirEstatisticas()
  }

  imprimirTipos(){
    const $tipos = document.getElementById( 'tipos' )

    for(let tipo of this.pokemon.tipos){
      this.li = document.createElement( 'li' )
      this.li.innerText = tipo
      $tipos.appendChild( this.li )
    }

  }

  imprimirEstatisticas(){
    const $estatistica = document.getElementById( 'estatistica' )

    for(let estatistica of this.pokemon.estatisticas){
      const li = document.createElement( 'li' )
      const div = document.createElement( 'div' )
      const h4 = document.createElement( 'h4' )
      const p = document.createElement( 'p' )
      h4.innerText = `${estatistica.stat.name}: `
      p.innerText = `${estatistica.base_stat}`

      div.className = "dado"
      div.appendChild(h4)
      div.appendChild(p)
      li.appendChild(div)
      $estatistica.appendChild( li )
    }
  }

  limparListas(){
    const $tipos = document.getElementById( 'tipos' )

    while ($tipos.hasChildNodes()) {   
      $tipos.removeChild($tipos.firstChild);
    }

    const $estatistica = document.getElementById( 'estatistica' )

    while ($estatistica.hasChildNodes()) {   
      $estatistica.removeChild($estatistica.firstChild);
    }
  }

}
