package lovecraftmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lovecraftmod.patches.AbstractCardEnum;
import lovecraftmod.powers.DreamManip;
import lovecraftmod.powers.Immortality; 
import lovecraftmod.powers.Mad_Induce; 
import lovecraftmod.actions.Horror;
import lovecraftmod.actions.Madness;

public class cthulhu extends AbstractLovecraftCard {
  public static final String ID = "Cthulhu";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 3;
  private static final int POWER_BLOCK = 999;
  private static final int MADNESS = 1;
  private static final int HORROR = 1;
  private int DREAM_INTENT;
  private static final int POWER_UPGRADE = 3;
  private static final int POOL = 1;

   public cthulhu() {
      super(ID, NAME, "img", COST, DESCRIPTION, AbstractCard.CardType.POWER,
              AbstractCardEnum.BLACK, AbstractCard.CardRarity.COMMON,
              AbstractCard.CardTarget.SELF, POOL);
    this.block = this.baseBlock = POWER_BLOCK;  
            }

// call DreamManip, Immortality, Mad_Induce (upgrade)
// DreamManip: debuff - buff/debuff cancel + attack cancel + goodies
// Immortality: buff - 3 turns of 999 block + no curse + no debuff
// Upgrade: Mad_Induce + BaseCost Reduction
// Mad_Induce: +3 Madness & Attack 2 * Madness to ea enemy
   @Override
   public void use(AbstractPlayer p, AbstractMonster m) {
     // Immortality... and GO
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Immortality(p, this.block), this.block)); 
      
      
      // Dream Manipulation... and GO
      DREAM_INTENT = 0;
      
      switch (DREAM_INTENT) {
      
          case 1:
              // Manipulation: (buffs/debuffs)
              // +1 Madness
              AbstractDungeon.actionManager.addToBottom(new Madness(p, m, MADNESS));
              break;
          case 2:
              //Dream: horror (attacks)
              AbstractDungeon.actionManager.addToBottom(new Horror(m, p, HORROR));
              break;
          default:
              // +2 madness
              AbstractDungeon.actionManager.addToBottom(new Madness(p, m, MADNESS*2));
              break;
      }
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DreamManip(m, DREAM_INTENT), DREAM_INTENT));
   }
   
   @Override
   public AbstractCard makeCopy() {
       return new cthulhu();
   }
   
   @Override
   public void upgrade() {
       
   }
}
