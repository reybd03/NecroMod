package lovecraftmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.AbstractPower;

import lovecraftmod.lovecraftmod;
/* DreamManip: debuff 
* if enemy buffs/attacks: +1 stack of horror (frail + weak)
* if enemy debuffs: cancel
*/

public class DreamManip extends AbstractPower {
    public static final String POWER_ID = "DreamManip";
    public static final String NAME = "Dream Manipulation";
    public static final String[] DESCRIPTIONS = new String[] {
      "description1",
      "description2"
    };
 
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    
    public DreamManip(AbstractCreature owner, int DREAM_INTENT) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = DREAM_INTENT;
        updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = false;
        this.img = new Texture("img");
        
        moveByte = 1;
        moveIntent = AbstractMonster.Intent.UNKNOWN;
        
        if(owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)owner;
            
            moveByte = Byte.valueOf(m.nextMove);
            moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());
            
            if (this.amount < 0) { // monster buff/debuff
                m.setMove(Byte.MAX_VALUE, AbstractMonster.Intent.STUN);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, Byte.MAX_VALUE, AbstractMonster.Intent.STUN));
            }
        }
    }
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        
        if (owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)owner;
            
            m.setMove(moveByte, moveIntent);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, moveByte, moveIntent));
            m.updatePowers();
        }
    }
}
