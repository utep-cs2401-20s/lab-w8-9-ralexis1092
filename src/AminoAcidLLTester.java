import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTester {



    /* Test Case 1
     * Method: createFromRNASequence()
     *
     * Input: "UGUGGUUGCCCAUUUCCCUUACCU"
     *
     * Expected Outcome:  char [] aminoEX = {'C','G','P','F','L'};
     *                    int[] countEX = {2,1,3,1,1};
     *
     * Reason: This a common and expected use of the method without any type of edge case.
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
     * Method: createFromRNASequence()
     *
     * Input: "CCGUUGGCACUGUGGCCCUAGACU"
     *
     * Expected Outcome:  char[] aminoEX = {'P','L','A','W'};
     *                    int[] countEX = {2,2,1,1};
     *
     * Reason: Unlike the previous test which provided a common use of an Rna list, this test contains a stop sequence
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

    /* Test Case 3
     * Method: aminoAcidCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("ACGACGAGGAGGAGGAGGGAG")
     *          char[] aminoAcids = {'E','R','T'};
     *          int[] counters = {1,4,2};
     *
     *          List 2:
     *          createFromRNASequence("ACGCGAACGGAA")
     *          char[] aminoAcids = {'E','R','T'};
     *          int[] counters = {1,1,2};
     *
     * Expected Outcome:  3
     *
     * Reason: For this test i decided to use two lists which have identical amino acids but different counts to test
     *          the matching recursion method which called the method totalDiff and I wanted to also test the base case
     *          in which they were equal
     *
     * Outcome: The code ran surprisingly well since it entered the same recursion path until landing in the base case.
     */
    @Test
    void aminoAcidCompareTest3() {


        AminoAcidLL a = AminoAcidLL.createFromRNASequence("ACGACGAGGAGGAGGAGGGAG");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("ACGCGAACGGAA");
        b = AminoAcidLL.sort(b);

        int c = a.aminoAcidCompare(b);
        assertEquals(3,c);


    }

    /* Test Case 4
     * Method: aminoAcidCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("CCGUUGGCACUGUGGCCCUAGACU")
     *          char[] aminoAcids = {'A','L','P'};
     *          int[] counters = {1,2,1};
     *
     *          List 2:
     *          createFromRNASequence("UGGCCC")
     *          char[] aminoAcids = {'P','W'};
     *          int[] counters = {1,1};
     *
     * Expected Outcome:  4
     *
     * Reason: For this test i decided to use two lists which have different sizes to see if the code could correctly
     *          pick a recursive path in which it correctly calculated the total differences of the counters Arrays.
     *
     * Outcome: Even though at the beginning the code returned 5 instead of 4 i fixed the way in which it would operate
     *          when one list was at the end to correctly count the difference. Overall, though most of the development
     *          of this method, the difference of amino acids was a huge edge case, which got solved in this test case.
     */
    @Test
    void aminoAcidCompareTest4() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUG");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("UGGCCC");
        b = AminoAcidLL.sort(b);

        int c = a.aminoAcidCompare(b);
        assertEquals(4,c);

    }

    /* Test Case 5
     * Method: aminoAcidCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("CCGUUUUUU")
     *          char[] aminoAcids = {'P','F'};
     *          int[] counters = {1,2};
     *
     *          List 2:
     *          createFromRNASequence("CCGUUGGCACUGCUG")
     *          char[] aminoAcids = {'A','L','P'};
     *          int[] counters = {1,3,1};
     *
     * Expected Outcome:  6
     *
     * Reason: For this test case I used a larger list as well as one different amino acid to test the case
     *         in which list 1 is null and the last two amino acids are not equal.
     *
     * Outcome: The outcome was expected since I just inverted the test case of when list 2 is null so that it works
     *           accordingly with case in which list 1 is null.
     */
    @Test
    void aminoAcidCompareTest5() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUUUUU");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("CCGUUGGCACUGCUG");
        b.printInformation();
        b = AminoAcidLL.sort(b);

        int c = a.aminoAcidCompare(b);
        assertEquals(6,c);

    }


    /* Test Case 6
     * Method: codonCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("ACGACGAGGAGGAGGAGGGAG")
     *          char[] aminoAcids = {'E','R','T'};
     *          int[] counters = {1,4,2};
     *
     *          List 2:
     *          createFromRNASequence("ACGCGAACGGAA")
     *          char[] aminoAcids = {'E','R','T'};
     *          int[] counters = {1,1,2};
     *
     * Expected Outcome:  7
     *
     * Reason: In this case i used the same amino acids to properly test the comparisons of the different codons
     *          used to create the list, which created a huge difference to the aminoAcidCompare method.
     *
     * Outcome: The return was 7 as expected meaning that the code ran well
     */

    @Test
    void codonCompareTest6() {


        AminoAcidLL a = AminoAcidLL.createFromRNASequence("ACGACGAGGAGGAGGAGGGAG");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("ACGCGAACGGAA");
        b = AminoAcidLL.sort(b);


        int c = a.codonCompare(b);
        assertEquals(7,c);


    }

    /* Test Case 7
     * Method: codonCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("CCGUUGGCACUGUGGCCCUAGACU")
     *          char[] aminoAcids = {'A','L','P'};
     *          int[] counters = {1,2,1};
     *
     *          List 2:
     *          createFromRNASequence("UGGCCC")
     *          char[] aminoAcids = {'P','W'};
     *          int[] counters = {1,1};
     *
     * Expected Outcome:  6 (since the amino acid 'P' originates from different codons)
     *
     * Reason: For this test i decided to use two lists which contained one common amino acid which originated
     *          from a different codon. This means that the expected anwser would be different from using the
     *          similar method aminoAcidCompare. I also used a larger list 1 than to test other recursion paths.
     *
     * Outcome: With using the similar method strucuture from the method AminoAcidCompare but changing
     *          the cases in which the amino acids were the same, the method functioned properly as it returned a 6
     */
    @Test
    void codonCompareTest7() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUGGCACUG");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("UGGCCC");
        b = AminoAcidLL.sort(b);

        int c = a.codonCompare(b);
        assertEquals(6,c);

    }

    /* Test Case 8
     * Method: codonCompare()
     *
     * Input:   List 1:
     *          createFromRNASequence("CCGUUUUUU")
     *          char[] aminoAcids = {'P','F'};
     *          int[] counters = {1,2};
     *
     *          List 2:
     *          createFromRNASequence("CCAUUGGCACUGUGGCCCUAGACU")
     *          char[] aminoAcids = {'A','L','P'};
     *          int[] counters = {1,2,1};
     *
     * Expected Outcome:  7
     *
     * Reason: For this test i decided to use two lists which contained one common amino acid which originated
     *          from a different codon. This means that the expected anwser would be different from using the
     *          similar method aminoAcidCompare. In this test, I used a larger list to test possible paths left out
     *          during the other cases such as when list 1 is null.
     *
     * Outcome: With using the similar method structure from the method AminoAcidCompare but changing
     *          the cases in which the amino acids were the same, the method functioned properly as it returned a 7
     */
    @Test
    void codonCompareTest8() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCGUUUUUU");
        a = AminoAcidLL.sort(a);

        AminoAcidLL b = AminoAcidLL.createFromRNASequence("CCAUUGGCACUG");
        b = AminoAcidLL.sort(b);

        int c = a.codonCompare(b);
        assertEquals(7,c);

    }


    /* Test Case 9
     * Method: sort()
     *
     * Input:   List:     createFromRNASequence("UCGCCGUUGGCAUUG")
     *                    char[] aminoEX = {'S','P','L','A'};
     *                    int[] countEX = {1,1,2,1};
     *
     *
     * Expected Outcome:  char[] aminoEX = {'A','L','P','S'};
     *                    int[] countEX = {1,2,1,1};
     *
     * Reason: For this I decided to use a list with different amino acids throughout the alphabet who dont start
     *          sorted when created from the RNA sequence which have different counters
     *
     * Outcome: The code properly returned arrays which data corresponded with eachother meaning that it properly
     *          sorted the array
     */
    @Test
    void sort9() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("UCGCCGUUGGCAUUG");
        a = AminoAcidLL.sort(a);

        char[] aminoEX = {'A','L','P','S'};
        int[] countEX = {1,2,1,1};

        int[] outcomeCount = a.aminoAcidCounts();
        char[] outcomeAmino = a.aminoAcidList();
        Assert.assertArrayEquals(countEX,outcomeCount);
        Assert.assertArrayEquals(aminoEX,outcomeAmino);

    }

    /* Test Case 10
     * Method: sort()
     *
     * Input:   List :    createFromRNASequence("UCGCCGUUGGCAUUG")
     *                    char[] aminoEX = {'P','G','F','E'};
     *                    int[] countEX = {1,2,3,4};
     *
     *
     * Expected Outcome:  char[] aminoEX = {'E','F','G','P'};
     *                     int[] countEX = {4,3,2,1};
     *
     * Reason: For this test I used an RNA sequance to create a list that would be completely backwards as well as each
     *         amino acid decreasing in counts alphabetically.
     *
     *
     * Outcome: The code properly returned arrays which data corresponded with each other meaning that it properly
     *          sorted the list
     */
    @Test
    void sort10() {

        AminoAcidLL a = AminoAcidLL.createFromRNASequence("CCCGGGGGGUUUUUUUUUGAGGAGGAGGAG");
        a = AminoAcidLL.sort(a);

        char[] aminoEX = {'E','F','G','P'};
        int[] countEX = {4,3,2,1};

        int[] outcomeCount = a.aminoAcidCounts();
        char[] outcomeAmino = a.aminoAcidList();
        Assert.assertArrayEquals(countEX,outcomeCount);
        Assert.assertArrayEquals(aminoEX,outcomeAmino);

    }



}