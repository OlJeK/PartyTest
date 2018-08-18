package com.party.oljek

import com.party.oljek.command.PartyCommand
import com.party.oljek.data.DataParty
import com.party.oljek.interfaces.IDataParty
import net.md_5.bungee.api.plugin.Plugin

class Party: Plugin() {

    companion object {
        val dataParty: IDataParty = DataParty()
        lateinit var instance: Party
    }

    init {
        instance = this
    }

    override fun onEnable() {
        registerCommand()
    }

    override fun onDisable() {

    }

    private fun registerCommand() {
        proxy.pluginManager.registerCommand(this, PartyCommand())
    }

}