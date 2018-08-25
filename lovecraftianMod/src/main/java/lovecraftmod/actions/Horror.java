/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftmod.actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;

/**
 *
 * @author rey
 */

public class Horror extends AbstractGameAction {
    
    public Horror (final AbstractCreature target, final AbstractCreature source, final int amount) {
        this.setValues(target, source, amount);
    }
 @Override
 public void update() {
     if (!this.target.hasPower("Artifact")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, -this.amount), -this.amount));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, (this.amount * 2), false), (this.amount * 2), true,AbstractGameAction.AttackEffect.NONE));
     }
     this.isDone = true;
 }
    
}
