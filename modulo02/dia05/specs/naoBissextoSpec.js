describe('naoBissexto', function () {
  const expect = chai.expect
  // antes de cada teste, chama a biblioteca "chai" para poder usar as asserções de forma mais amigável (should, expect)
  // documentação: https://www.chaijs.com/
  beforeEach(function () {
    chai.should()
  })

  // cada bloco "it" é um cenário de teste, como se fosse um @Test do JUnit
  // importante: garanta que o arquivo onde o código está implementado foi incluído, em rodar-testes.html
  it('1988 é bissexto', function () {
    const ano = 1988
    const resultado = naoBissexto(ano)
    resultado.should.be.false
  })

  it('2000 é bissexto', function () {
    const ano = 2000
    const resultado = naoBissexto(ano)
    resultado.should.be.false
  })

  it('2016 é bissexto', function () {
    const ano = 2016
    const resultado = naoBissexto(ano)
    resultado.should.be.false
  })

  it('2020 é bissexto', function () {
    const ano = 2020
    const resultado = naoBissexto(ano)
    resultado.should.be.false
  })

  it('2001 não é bissexto', function () {
    const ano = 2001
    const resultado = naoBissexto(ano)
    resultado.should.be.true
  })

  it('2025 não é bissexto', function () {
    const ano = 2025
    const resultado = naoBissexto(ano)
    resultado.should.be.true
  })
})
