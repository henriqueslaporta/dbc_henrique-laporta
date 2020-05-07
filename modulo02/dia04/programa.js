console.log("Começou!")

/*
* Exemplo comentário
*/

// Exemplo comentário

// Criar variavel
var nome = "Henrique"

// Criar variavel constante (nao e possivel atribuir)
var constante = "Henrique"

// Concatenar Strings
var s1 = 1 + "2" + 3

// primeiro Somar e depois Concatenar Strings
var s2 = 1 + 2 + "3"

// Concatenar Strings
var s3 = "1" + true

// Isso é um NaN
var nan = 1/"zero"

// Verificar se é um numero opu NaN
isNaN(1/"zero")

//--------------------| ARRAYS |-------------------------------------------

var numeros = [1,2,3]

// Ordenar
numeros.sort();

// Atribuir campos ao array
numeros.nome = "Array"

// Pode atribuir elementos em qualquer posição, o lenght é igual a ultima posicao + 1
numeros[99] = "oi"

// Agora numeros.lenght = 100

// Posicoes sem preenchimento no array é undefined

// pode definir como null e o typeof de null é Object


//--------------------| "Objetos" |-------------------------------

// Legolas esa num escopo global
var legolas = {
  nome: "Legolas",
  experiencia: 0,
  qtdFlechas: 7,
  // Declarando uma função interna do Legolas
  atirarFlecha: function(nome = "ERROR"){
    this.qtdFlechas--
    this.experiencia++
    return nome + "!!!"
  }
}

/*   IMPORTANTE

THIS aponta sempre para o contexto de execução

Exemplo: se criar uma variavel apontanto outra função interna legolas

var outraFunc = legolas.atirarFlecha

quando executar outraFunc o contexto de execução não é legolas assim o this não funciona o (this.qtdFlechas--)

*/

/* funcao sendo declarada tambem internamente no legolas

legolas.atirarFlecha = function(){
  legolas.qtdflechas--
  legolas.experiencia++
}

*/

/* funcao sendo declarada globalmentete

function atirarFlecha(){
  legolas.qtdflechas--
  legolas.experiencia++
}

*/

// Pattern Matching
var nome = "Elfo"
const experiencia = 0
const qtdFlechas = 7
const atirarFlecha = function(nome = "ERROR"){
  this.qtdFlechas--
  this.experiencia++
  return nome + "!!!"
}

const outroElfo = { nome, experiencia, qtdFlechas, atirarFlecha }

function imprimirElfo( elfo ){
  const { experiencia, qtdFlechas } = elfo
  console.log( experiencia + qtdFlechas )
}
