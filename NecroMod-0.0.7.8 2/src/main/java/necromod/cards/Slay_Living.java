package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.rooms.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;


public class Slay_Living extends AbstractNecromancerCards {
	public static final String ID = "Slay_Living";
	public static final String NAME = "Slay Living";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 10;
	public static final String DESCRIPTION = "When the target has 30% or less HP remaining : Kill it instantly.";
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public static final String[] EXTENDED_DESCRIPTION = new String[] {
			"The target is above 30% HP."
	};
	
	public static final String DESCRIPTION_ON_DEADLY = "Kills target instantly.";
	public final String UPGRADE_DESCRIPTION = "When the target has 40% or less HP remaining : Kill it instantly.";
	public static final String UPGRADE_DESCRIPTION2 = "When the target has 40% or less HP remaining : Kill it instantly.";
	
	public AbstractMonster target;
	public int HPThreshhold;
	public double Multiplier = 0.3;
	
	public Slay_Living() {
		super (ID, NAME, NecroMod.makePath(NecroMod.SLAY_LIVING), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 3;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			this.HPThreshhold = ((int) (m.maxHealth*this.Multiplier-1));
			
			if(m.currentHealth <= HPThreshhold) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, 999, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
			}
			else {
				AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, Slay_Living.EXTENDED_DESCRIPTION[0], true));
	            return;
			}
			if(m.hb.hovered && (m.currentHealth <= HPThreshhold)) {
				
			}
			//if((Slay_Living.CardTarget.ENEMY && Slay_Living.CardTarget.) {
				
			//}
    }
	@Override
	public void update() {
		super.update();	
		if(AbstractDungeon.player != null) {
		if(AbstractDungeon.getCurrRoom() == null || AbstractDungeon.getCurrRoom() instanceof MonsterRoom || AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite || AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
		
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				this.HPThreshhold = ((int) (mo.maxHealth*this.Multiplier));
				
				if(AbstractDungeon.isScreenUp = false ) { //|| AbstractDungeon.screen != AbstractDungeon.CurrentScreen.CARD_REWARD
										
					if(mo.hb.hovered && (mo.currentHealth <= HPThreshhold)) {
						this.rawDescription = Slay_Living.DESCRIPTION_ON_DEADLY;
						this.initializeDescription();
					}
					else {
						if(!this.upgraded) {
							mo.unhover();
							this.rawDescription = Slay_Living.DESCRIPTION;
							this.initializeDescription();
						}
						else {
							mo.unhover();
							this.rawDescription = Slay_Living.UPGRADE_DESCRIPTION2;
						}
						
					}
				}
					


			}
		}	
	}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Slay_Living();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.Multiplier = 0.4;
            this.rawDescription = this.UPGRADE_DESCRIPTION;
	        this.initializeDescription();
        }
	
    }

}
