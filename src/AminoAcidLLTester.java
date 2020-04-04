import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTester {


    @Test
    void sort3() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUG");
        a = AminoAcidLL.sort(a);
        a.printInformation();
        System.out.println(a.isSorted());
        System.out.println();

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("UGGCCC");
        b = AminoAcidLL.sort(b);
        b.printInformation();
        System.out.println(b.isSorted());
        System.out.println();

        int c = a.aminoAcidCompare(b);
        System.out.println(c);
        int d = a.codonCompare(b);
        System.out.println(d);


    }
    /* Test Case 1
     * Method: createFromRNASequence
     *
     * Input: "UGUGGUUGCCCAUUUCCCUUACCU"
     *
     * Expected Outcome:  char [] aminoEX = {'C','G','P','F','L'};
     *                    int[] countEX = {2,1,3,1,1};
     *
     *  Reason: This a common and expected use of the method without any type of edge case.
     *          It is also long and contains multiple of the same Amino Acids to check if the code could detect which
     *          codons they originated as shown in the char array.
     *
     * Outcome: The code managed to perfectly count the number of codons in which each Amino Acid originated as well
     *          as store them in the same order as the amino acids on the other array.
     */
    @Test
    void createFromRnaSequenceTest1() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("UGUGGUUGCCCAUUUCCCUUACCU");

        char[] aminoEX = {'C','G','P','F','L'};
        int[] countEX = {2,1,3,1,1};

        int[] outcomeCount = a.aminoAcidCounts();
        char[] outcomeAmino = a.aminoAcidList();
        Assert.assertArrayEquals(countEX,outcomeCount);
        Assert.assertArrayEquals(aminoEX,outcomeAmino);

    }

    /* Test Case 2
     * Method: createFromRNASequence
     *
     * Input: "CCGUUGGCACUGUGGCCCUAGACU"
     *
     * Expected Outcome:  char[] aminoEX = {'P','L','A','W'};
     *                    int[] countEX = {2,2,1,1};
     *
     *  Reason: Unlike the previous test which provided a common use of an Rna list, this test contains a stop sequence
     *          before the actual sequence is finished. This will check if the code actually prevent the creation of any
     *          further amino acids on the sequence.
     *
     * Outcome: The code managed to perfectly stop at the right amino acid by using a stop rna sequence. It also,
     *          created a correct array storing the correct data about the amino acids and the counters.
     */
    @Test
    void createFromRnaSequenceTest2() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUGUGGCCCUAGACU");

        char[] aminoEX = {'P','L','A','W'};
        int[] countEX = {2,2,1,1};

        int[] outcomeCount = a.aminoAcidCounts();
        char[] outcomeAmino = a.aminoAcidList();
        Assert.assertArrayEquals(countEX,outcomeCount);
        Assert.assertArrayEquals(aminoEX,outcomeAmino);

    }

    /* Test Case 2
     * Method: createFromRNASequence
     *
     * Input: "CCGUUGGCACUGUGGCCCUAGACU"
     *
     * Expected Outcome:  char[] aminoEX = {'P','L','A','W'};
     *                    int[] countEX = {2,2,1,1};
     *
     *  Reason: Unlike the previous test which provided a common use of an Rna list, this test contains a stop sequence
     *          before the actual sequence is finished. This will check if the code actually prevent the creation of any
     *          further amino acids on the sequence.
     *
     * Outcome: The code managed to perfectly stop at the right amino acid by using a stop rna sequence. It also,
     *          created a correct array storing the correct data about the amino acids and the counters.
     */
    @Test
    void createFromRnaSequenceTest2() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUGUGGCCCUAGACU");

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUG");
        a = AminoAcidLL.sort(a);
        a.printInformation();
        System.out.println();

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("UGGCCC");
        b = AminoAcidLL.sort(b);
        b.printInformation();
        System.out.println();

        int c = a.aminoAcidCompare(b);
        System.out.println(c);

    }

}