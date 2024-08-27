package Raças

abstract class Raca {
    abstract val bonusHabilidades: Map<String, Int>
    fun obterHabilidadesIniciais(): Map<String, Int> {
        return mapOf(
            "Força" to 8,
            "Destreza" to 8,
            "Constituição" to 8,
            "Inteligência" to 8,
            "Sabedoria" to 8,
            "Carisma" to 8
        )
    }
}