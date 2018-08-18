package com.party.oljek.command

import com.party.oljek.Party
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

class PartyCommand: Command("party") {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {

        if (sender !is ProxiedPlayer)
            return

        if (args!!.isEmpty()) {
            sender.sendMessage(TextComponent("null arguments!"))
            return
        }

        when (args[0]) {

            "i" -> {
                if (Party.dataParty.isOwner(sender) || Party.dataParty.isPlayer(sender)) {
                    val party = Party.dataParty.getParty(sender)
                    sender.sendMessage(TextComponent("Вы уже в пати под ид: ${party!!.id}"))
                    return
                }

                val target: ProxiedPlayer? = Party.instance.proxy.getPlayer(args[1])

                if (target == null) {
                    sender.sendMessage(TextComponent("Target is null!"))
                    return
                }

                val partyInfo = Party.dataParty.createParty(sender, target)

                sender.sendMessage(TextComponent("Владелец: ${partyInfo!!.owner} ид: ${partyInfo!!.id}"))
                return
            }

            "d" -> {

            }

            "l" -> {
                if (!Party.dataParty.isPlayer(sender) || Party.dataParty.isOwner(sender)) {
                    sender.sendMessage(TextComponent("Вы не являетесь участником пати (игроком)"))
                    return
                }

                Party.dataParty.leaveParty(sender)
                sender.sendMessage(TextComponent("Вы вышли из пати!"))
                return
            }

            "s" -> {
                sender.sendMessage(TextComponent("${Party.dataParty.getSize(args[1].toInt())}"))
                return
            }

            else -> {
                sender.sendMessage(TextComponent("null argument!"))
                return
            }

        }

        return
    }

}