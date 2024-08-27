interface CustoStrategy {
    fun calcularCusto(valorAtual: Int, valorDesejado: Int): Int
}


class CustoPadraoStrategy : CustoStrategy {
    private val tabelaCusto = mapOf(
        9 to 1,
        10 to 1,
        11 to 1,
        12 to 1,
        13 to 1,
        14 to 2,
        15 to 2
    )

    override fun calcularCusto(valorAtual: Int, valorDesejado: Int): Int {
        var custo = 0
        for (valor in (valorAtual + 1)..valorDesejado) {
            custo += tabelaCusto[valor] ?: 0
        }
        return custo
    }
}


fun calcularCusto(strategy: CustoStrategy, valorAtual: Int, valorDesejado: Int): Int {
    return strategy.calcularCusto(valorAtual, valorDesejado)
}


object ModificadoresHabilidade {
    val tabelaModificador = mapOf(
        1 to -5,
        2 to -4,
        3 to -4,
        4 to -3,
        5 to -3,
        6 to -2,
        7 to -2,
        8 to -1,
        9 to -1,
        10 to 0,
        11 to 0,
        12 to 1,
        13 to 1,
        14 to 2,
        15 to 2,
        16 to 3,
        17 to 3,
        18 to 4,
        19 to 4,
        20 to 5,
        21 to 5,
        22 to 6,
        23 to 6,
        24 to 7,
        25 to 7,
        26 to 8,
        27 to 8,
        28 to 9,
        29 to 9,
        30 to 10
    )

    fun getModificador(valor: Int): Int {
        return tabelaModificador[valor] ?: 0
    }
}