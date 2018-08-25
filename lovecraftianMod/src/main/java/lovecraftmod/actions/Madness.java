/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftmod.actions;

/**
 *
 * @author rey
 */

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;

public class Madness extends AbstractGameAction {
    public Madness(AbstractCreature target, AbstractCreature source, int amount) {
        this.setValues(target, source, amount);
    }
 @Override
 public void update() {
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, +this.amount), +this.amount));
 
     this.isDone = true;
 }
 
}
