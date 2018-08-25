package lovecraftmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import lovecraftmod.patches.AbstractCardEnum;
import lovecraftmod.powers.DreamManip; // fill in with Cthulhu Powers

public class cthulhu extends AbstractLovecraftCard {
  public static final String ID = "Cthulhu";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 3;
  private static final int POOL = 1;

   public cthulhu() {
      super(ID, NAME, "img", COST, DESCRIPTION, AbstractCard.CardType.POWER,
              AbstractCardEnum.LOVECRAFTMOD, AbstractCard.CardRarity.COMMON,
              AbstractCard.CardTarget.SELF, POOL);
            }

// call DreamManip, Immortality, Mad_Induce (upgrade)
   @Override
   public void use(AbstractPlayer p, AbstractMonster m) {
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DreamManip(p, this.magicNumber), this.magicNumber));
   }
}
