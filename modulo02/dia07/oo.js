class Elfo{
  constructor(nome){
    this._nome = nome,
    this.qtdFlechas = 7
  }

  static qtdElfos(){
    return 2
  }

  get nome(){
    return this._nome + " Funghi"
  }

  set nome( nome ){
    this._nome = nome
  }

  atirarFlecha() {
    console.log( "random" )
  }
}

// Elfo.prototype.atirarFlecha = function() {
//   console.log( "random" )
// }

const legolas = new Elfo ('Legolas')
console.log( legolas.nome )
legolas.atirarFlecha()
