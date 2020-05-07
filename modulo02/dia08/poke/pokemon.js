class Pokemon{
  constructor(dados){
    this.nome = dados.name,
    this.img = dados.sprites.front_default,
    this.id = dados.id,
    this.altura = dados.height * 10,
    this.peso = dados.weight / 10,
    this.tipos = dados.types.map( t => t.type.name ),
    this.estatisticas = dados.stats
  }
}
