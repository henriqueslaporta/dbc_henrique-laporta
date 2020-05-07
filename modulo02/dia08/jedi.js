class Jedi{
  constructor( nome ){
    this.nome = nome

    this.h1 = document.createElement( 'h1' )
    this.h1.innerText = this.nome
    this.h1.id = `jedi_${ this.nome }`
    const dadosJedi = document.getElementById( 'dadosJedi' )
    dadosJedi.appendChild( this.h1 )
  }

  atacar() {
    // const h1 = document.getElementById ( `jedi_${ this.nome }`)
    // let self = this  // Salvando o this para uso futuro 
    // arrowFunction mantes o contexto do THIS
    setTimeout( () => {
      // self.h1.innerText += ' atacou!'
      this.h1.innerText += ' atacou!'
    }, 1000)
  }
}
