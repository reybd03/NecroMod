/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import lovecraftmod.lovecraftmod;
import lovecraftmod.patches.AbstractCardEnum;

public class Strike_B extends AbstractLovecraftCard{
	public static final String ID = "Strike_B";
	public static final String NAME = "Strike";
	public static final String DESCRIPTION = "Deal !D! damage.";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int POOL = 0;

	public Strike_B() {
		super(ID, NAME, lovecraftmod.makePath(lovecraftmod.STRIKE_B), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.BLACK, AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.ENEMY, POOL);

		this.baseDamage = ATTACK_DMG;
	}

	public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
		if (com.megacrit.cardcrawl.core.Settings.isDebug) {
			if (com.megacrit.cardcrawl.core.Settings.isInfo) {
				this.multiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
				for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
					this.multiDamage[i] = 150;
				}
				AbstractDungeon.actionManager.addToBottom(
						new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage,
								this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			} else {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
						new DamageInfo(p, 150, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	public AbstractCard makeCopy() {
		return new Strike_B();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}