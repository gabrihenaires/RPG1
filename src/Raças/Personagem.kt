package Raças

class Personagem(
    val nome: String,
    val raca: Raca,
    val descricao: String,
    val habilidadesComBonus: Map<String, Int>,
    val modificadoresHabilidade: Map<String, Int>,
    val habilidadesFinais: Map<String, Int>,
    val pontosDeVida: Int
) {
    fun exibirInfo() {
        println("\n--- Ficha do Personagem ---")
        println("Nome: $nome")
        println("Raça: ${raca.javaClass.simpleName}")
        println("Descrição: $descricao")
        println("Habilidades com Bônus: $habilidadesComBonus")
        println("Modificadores de Habilidade: $modificadoresHabilidade")
        println("Habilidades Finais: $habilidadesFinais")
        println("Pontos de Vida: $pontosDeVida")
    }
}