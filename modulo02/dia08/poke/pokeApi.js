class PokeApi {
  constructor( url ) {
    this.url = url
  }

  buscar( idPokemon ) {
    let urlPokemon = `${ this.url }/${ idPokemon }/`
    return fetch( urlPokemon )
  }
}
