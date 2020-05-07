describe('concatenarSemUndefined', function () {
  const expect = chai.expect

  beforeEach(function () {
    chai.should()
  })

  it('texto nos dois parametros', function () {
    concatenarSemUndefined( 'Primeiro', ' Segundo' ).should.equal( 'Primeiro Segundo' )
  })

  it('texto undefined no primeiro parametro', function () {
    concatenarSemUndefined( undefined , 'Segundo' ).should.equal( 'Segundo' )
  })

  it('texto undefined no segundo parametro', function () {
    concatenarSemUndefined( 'Primeiro' , undefined ).should.equal( 'Primeiro' )
  })

  it('texto undefined nos dois parametros', function () {
    concatenarSemUndefined( undefined , undefined ).should.equal( '' )
  })

  it('Sem os dois parametros', function () {
    concatenarSemUndefined( ).should.equal( '' )
  })
  
  it('Só um parametro', function () {
    concatenarSemUndefined( 'Um' ).should.equal( 'Um' )
  })

})
