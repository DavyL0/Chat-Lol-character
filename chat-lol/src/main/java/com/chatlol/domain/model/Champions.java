package com.chatlol.domain.model;

public record Champions(
        Long id,
        String name,
        String role,
        String lore,
        String imageUrl
) {
        public String generateContextByQuestion(String question) {
            return """
                    Nome do Campeão: %s
                    Função: %s
                    Lore: %s
                    """.formatted(this.name, this.role, this.lore);
        }
}
