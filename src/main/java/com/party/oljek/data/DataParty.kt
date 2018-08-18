package com.party.oljek.data

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.party.oljek.`object`.ResultParty
import com.party.oljek.interfaces.IDataParty
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

class DataParty: IDataParty {

    companion object {
        private var partys: MutableMap<Int, PartyInfo> = Maps.newHashMap()
    }

    override fun getSize(id: Int): Int = partys[id]!!.players.size + 1

    override fun isOwner(p: ProxiedPlayer): Boolean {
        var party: PartyInfo = getParty(p) ?: return false

        return party.owner == p
    }

    override fun isPlayer(p: ProxiedPlayer): Boolean {
        var party: PartyInfo = getParty(p) ?: return false

        return party.players.contains(p)
    }

    override fun getPlayers(id: Int): List<ProxiedPlayer> {
        var party: PartyInfo = getParty(id) ?: return Lists.newArrayList()

        return party.players
    }

    override fun createParty(owner: ProxiedPlayer, p: ProxiedPlayer): PartyInfo? {
        var isParty: Boolean = !(isOwner(p) || isPlayer(p))

        if (!isParty)
            return PartyInfo(null, Lists.newArrayList(), 0)

        val partyInfo = PartyInfo(owner, ArrayList<ProxiedPlayer>(Arrays.asList(p)), partys.size + 1)
        partys[partyInfo.id] = partyInfo
        return partyInfo
    }

    override fun leaveParty(p: ProxiedPlayer): Boolean {
        if (!isPlayer(p) || isOwner(p))
            return false

        val party = getParty(p) ?: return false

        party.players.remove(p)
        return true
    }

    override fun disbandParty(owner: ProxiedPlayer) {
        if (isPlayer(owner) || !isOwner(owner))
            return

        val party = getParty(owner) ?: return

        partys.remove(party.id)
    }

    override fun getParty(id: Int): PartyInfo? {
        return partys[id]
    }

    override fun kickPlayer(owner: ProxiedPlayer, p: ProxiedPlayer): ResultParty {
        if (!isOwner(owner))
            return ResultParty.NOT_OWNER

        if (!isPlayer(p))
            return ResultParty.NOT_CONTAINS_PLAYER

        val partyOwner = getParty(owner)
        val partyPlayer = getParty(p)

        if (partyOwner!!.id != partyPlayer!!.id)
            return ResultParty.DIFFERENT_PARTY

        partys[partyOwner.id]!!.players.remove(p)
        return ResultParty.OK
    }

    override fun sendOwner(owner: ProxiedPlayer, p: ProxiedPlayer): ResultParty {
        return ResultParty.NOT_OWNER
    }

    override fun contains(p: ProxiedPlayer): Boolean {
        return false
    }

    override fun getParty(p: ProxiedPlayer): PartyInfo? {
        for (party: PartyInfo in partys.values) {
            if (party.players.contains(p) || party.owner == p)
                return party
        }

        return null
    }

}