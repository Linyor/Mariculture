package mariculture.fishery.fish.dna;

import java.util.Random;

import mariculture.api.fishery.fish.FishDNABase;
import mariculture.api.fishery.fish.FishSpecies;

public class FishDNAGender extends FishDNABase {
    private Random rand = new Random();

    @Override
    public int[] getDominant(int option1, int option2, Random rand) {
        int dominance1 = 0;
        int dominance2 = 0;

        dominance1 = option1 == 0 ? 0 : 1;
        dominance2 = option1 == 0 ? 0 : 1;

        return swapDominance(dominance1, dominance2, option1, option2, rand);
    }

    @Override
    public Integer getDNAFromSpecies(FishSpecies species) {
        return rand.nextInt(2);
    }
}
