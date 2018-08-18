package com.party.oljek.data

import net.md_5.bungee.api.connection.ProxiedPlayer

class PartyInfo(var owner: ProxiedPlayer?, var players: MutableList<ProxiedPlayer>, val id: Int)