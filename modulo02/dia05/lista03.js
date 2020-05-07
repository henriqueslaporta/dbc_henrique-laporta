function calcularCirculo(objetoParam) {

  //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions#The_arrow_function_expression_(%3E)
  const tipoCalculo = {
    'A': raio => Math.PI * objetoParam.raio ** 2,
    'C': raio => 2 * Math.PI * objetoParam.raio
  }

  const funcao = tipoCalculo[objetoParam.tipoCalculo]

  if (typeof funcao === 'function')
    return funcao(objetoParam.raio)
  else
    return undefined
}

function naoBissexto(ano) {
  const ehBissexto = ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0))
  return !ehBissexto
}

function concatenarSemUndefined (texto1 , texto2) {
  if(typeof texto1 === 'undefined') texto1 = ""
  if(typeof texto2 === 'undefined') texto2 = ""
  return texto1 + texto2
}
