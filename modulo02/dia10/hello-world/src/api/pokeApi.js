import Pokemon from '../models/pokemon.js'

export default class PokeApi {
  constructor( ) {
    this.url = 'https://pokeapi.co/api/v2'
  }

  async listarPorTipo( tipoPokemon, qtdPokemons, pagina) {
    let urlPokemon = `${ this.url }/type/${ tipoPokemon }/`

    const fim = qtdPokemons * pagina
    const inicio = fim - qtdPokemons

    return new Promise( resolve => {
      fetch( urlPokemon )
        .then( j => j.json() )
        .then( p => {
          const pokemons = p.pokemon.slice( inicio, fim )
          const promisePokemons = pokemons.map( p => this.buscarPokemon( { urlPokemonPreenchida: p.pokemon.url }))
          Promise.all(promisePokemons).then( resultadoFinal => {
            resolve(resultadoFinal)
          })
        } )
    } ) 
  }

  async buscarPokemon( params ) {
    let urlPokemon = params.urlPokemonPreenchida || `${ this.url }/pokemon/${ params.idPokemon }/`
    return new Promise( resolve => {
      fetch( urlPokemon )
        .then( j => j.json() )
        .then( p => {
          const pokemon = new Pokemon( p )
          resolve( pokemon )
        } )
    } ) 
  }
}
