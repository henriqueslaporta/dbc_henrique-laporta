class PokeApi {
  constructor( url ) {
    this.url = url
  }

  async buscar( idPokemon ) {
    let urlPokemon = `${ this.url }/${ idPokemon }/`
    return new Promise( resolve => {
      fetch( urlPokemon )
        .then( j => j.json() )
        .then( p => {
          const pokemon = new Pokemon( p )
          resolve( pokemon )
        } )
    } ) 
    // fetch( urlPokemon )

  }
}
