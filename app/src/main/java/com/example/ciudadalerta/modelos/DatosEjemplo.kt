package com.example.ciudadalerta.modelos

val incidenciasEjemplo = listOf(
    Incidencia(
        1, "Baches en la Calle Av. Madrid", Categoria.BACHES,
        "Bache profundo junto al paso de peatones. Supone un riesgo para bicicletas y " +
                "vehiculos, especialmente por la noche.",
        "Hoy, 09:42", Estado.EN_CURSO, "Avenida de Madrid, 25"
    ),
    Incidencia(
        2, "Farola sin luz en el parque", Categoria.ALUMBRADO,
        "La farola de la entrada del parque no se enciende por la noche. El camino queda " +
                "completamente a oscuras y da bastante inseguridad.",
        "Hoy, 08:15", Estado.PENDIENTE, "Parque de la Constitucion, entrada norte"
    ),
    Incidencia(
        3, "Contenedores desbordados", Categoria.BASURA,
        "Los contenedores de la esquina estan llenos desde el fin de semana y hay bolsas " +
                "tiradas por la acera. Empieza a oler mal.",
        "Ayer, 20:30", Estado.RESUELTA, "Avenida de Andalucia, 52"
    ),
    Incidencia(
        4, "Banco roto en la plaza", Categoria.MOBILIARIO,
        "El banco de madera junto a la fuente tiene dos tablones partidos y es peligroso " +
                "sentarse en el.",
        "Ayer, 17:05", Estado.PENDIENTE, "Plaza del Ayuntamiento"
    ),
    Incidencia(
        5, "Bache profundo en la rotonda", Categoria.BACHES,
        "Al salir de la rotonda hay un hoyo muy profundo que no se ve bien cuando llueve. " +
                "Las motos son las que mas riesgo corren.",
        "Hace 2 dias, 13:20", Estado.EN_CURSO, "Rotonda de la Estacion"
    ),
    Incidencia(
        6, "Papelera caida en la avenida", Categoria.MOBILIARIO,
        "La papelera esta tirada en el suelo desde hace dias y la basura se esparce con el viento.",
        "Hace 3 dias, 11:10", Estado.RESUELTA, "Avenida de la Libertad, 3"
    )
)