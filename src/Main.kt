import Raças.*

fun main() {
    val racasDisponiveis = mapOf<String, Raca>(
        "Gnomo das Rochas" to gnomoRochas(),
        "Alto Elfo" to altoElfo(),
        "Anão" to anao(),
        "Anão da Colina" to anaoColina(),
        "Anão da Montanha" to anaoMontanha(),
        "Draconato" to draconato(),
        "Drow" to drow(),
        "Elfo" to elfo(),
        "Elfo da Floresta" to elfoFloresta(),
        "Gnomo" to gnomo(),
        "Gnomo da Floresta" to gnomoFloresta(),
        "Gnomo das Rochas" to gnomoRochas(),
        "Halfing" to halfing(),
        "Halfing Pés Leves" to halfingPesLeves(),
        "Halfing Robusto" to halfingRobusto(),
        "Humano" to humano(),
        "Meio Elfo" to meioElfo(),
        "Meio Orc" to meioOrc(),
        "Tiefing" to tiefling()
    )

    print("Digite o Nome de seu personagem: ")
    val nome: String = readLine().orEmpty()

    print("Digite a Raça ou Subraça de seu personagem (${racasDisponiveis.keys.joinToString(", ")}): ")
    val raca: Raca? = racasDisponiveis[readLine()]

    if (raca == null) {
        println("Raça ou Subraça inválida. Tente novamente.")
        return
    }

    print("Digite a descrição de seu personagem: ")
    val descricao: String = readLine().orEmpty()

    val habilidadesIniciais = mutableMapOf(
        "Força" to 8,
        "Destreza" to 8,
        "Constituição" to 8,
        "Inteligência" to 8,
        "Sabedoria" to 8,
        "Carisma" to 8
    )

    var pontosRestantes = 27
    val custoStrategy = CustoPadraoStrategy()

    while (pontosRestantes > 0) {
        println("Distribuição atual: $habilidadesIniciais")
        print("Escolha a habilidade para ajustar: ")
        val habilidade = readLine()?.takeIf { it in habilidadesIniciais.keys }

        if (habilidade == null) {
            println("Habilidade inválida. Tente novamente.")
            continue
        }

        print("Qual o valor total desejado para $habilidade? (Pontos restantes: $pontosRestantes): ")
        val valorDesejado = readLine()?.toIntOrNull()

        if (valorDesejado != null && valorDesejado in 8..15) {
            val valorAtual = habilidadesIniciais[habilidade]!!
            val custo = calcularCusto(custoStrategy, valorAtual, valorDesejado)

            if (custo <= pontosRestantes) {
                habilidadesIniciais[habilidade] = valorDesejado
                pontosRestantes -= custo
            } else {
                println("Você não tem pontos suficientes para atingir esse valor. Custo necessário: $custo, Pontos restantes: $pontosRestantes")
            }
        } else {
            println("Valor inválido. O valor deve estar entre 8 e 15.")
        }
    }

    val habilidadesComBonus = habilidadesIniciais.mapValues { (habilidade, valor) ->
        valor + (raca.bonusHabilidades[habilidade] ?: 0)
    }

    val modificadoresHabilidade = habilidadesComBonus.mapValues { (habilidade, valor) ->
        ModificadoresHabilidade.getModificador(valor)
    }

    val habilidadesFinais = habilidadesComBonus.mapValues { (habilidade, valor) ->
        valor + modificadoresHabilidade[habilidade]!!
    }

    val modificadorConstituicao = modificadoresHabilidade["Constituição"] ?: 0
    val pontosDeVida = 10 + modificadorConstituicao

    val personagem = Personagem(
        nome = nome,
        raca = raca,
        descricao = descricao,
        habilidadesComBonus = habilidadesComBonus,
        modificadoresHabilidade = modificadoresHabilidade,
        habilidadesFinais = habilidadesFinais,
        pontosDeVida = pontosDeVida
    )

    personagem.exibirInfo()
}