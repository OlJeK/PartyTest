package com.party.oljek.interfaces

import com.party.oljek.`object`.ResultParty
import com.party.oljek.data.PartyInfo
import net.md_5.bungee.api.connection.ProxiedPlayer

interface IDataParty {

    fun contains(p: ProxiedPlayer): Boolean

    fun isOwner(p: ProxiedPlayer): Boolean

    fun isPlayer(p: ProxiedPlayer): Boolean

    fun getPlayers(id: Int): List<ProxiedPlayer>

    fun createParty(owner: ProxiedPlayer, p: ProxiedPlayer): PartyInfo?

    fun leaveParty(p: ProxiedPlayer): Boolean

    fun disbandParty(owner: ProxiedPlayer)

    fun getParty(id: Int): PartyInfo?

    fun kickPlayer(owner: ProxiedPlayer, p: ProxiedPlayer): ResultParty

    fun sendOwner(owner: ProxiedPlayer, p: ProxiedPlayer): ResultParty

    fun getSize(id: Int): Int

    fun getParty(p: ProxiedPlayer): PartyInfo?

}