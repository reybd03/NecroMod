/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.BaseMod;
import basemod.interfaces.PostDrawSubscriber;
import lovecraftmod.lovecraftmod;
/**
 *
 * @author elhulk08
 * Immortality: buff - 3 turns of 999 block + no curse + no debuff
 */
public class Immortality extends AbstractPower{
    public static final String POWER_ID = "Immortality";
    public static final String NAME = "Immortality";
    public static final String[] DESCRIPTIONS = new String[] {
      "description1",
       "description2"
    };
    
 public Immortality(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     updateDescription();
     this.type = AbstractPower.PowerType.BUFF;
     this.isTurnBased = false;
     this.img = new Texture("img");
 }
 
 @Override
 public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
 }
 
 @Override
 public void atEndOfTurn(final boolean isPlayer) {
     if(!isPlayer) return;
     for(final AbstractCard c : AbstractDungeon.player.hand.group) {
         if(c.retain) {
             AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
         }
     }
 }
}
