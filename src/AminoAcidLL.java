class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){

    this.aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    //gets the matching codon list for the first amino acid
    this.codons = AminoAcidResources.getCodonListForAminoAcid(this.aminoAcid);
    //creates a counter list that compliments the codon array
    this.counts = new int[this.codons.length];
    //increases the counter for the current codon
    this.counterIncrease(inCodon);
    //Sets the next item in the list to null
    this.next = null;

  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){

    if(this.aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)){
      this.counterIncrease(inCodon);
    }
    else{
      if (this.next != null){
        this.next.addCodon(inCodon);
      }
      else{
        this.next = new AminoAcidLL(inCodon);
      }
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int codonCounter = 0;
    for(int i = 0; i < this.counts.length;i++){
      codonCounter += this.counts[i];
    }
    return codonCounter;
  }

  
  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }


  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    //If both list are on their last amino acid
    if(this.next == null && inList.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.totalDiff(inList);
      }
      return this.totalCount() + inList.totalCount();
    }
    //If this instance points to null
    if(this.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.totalDiff(inList) + aminoCountHelper(inList.next);
      }
      if(this.aminoAcid < inList.aminoAcid){
        return this.totalCount() + aminoCountHelper(inList.next);
      }
      return inList.totalCount() + this.aminoAcidCompare(inList.next);
    }
    //If inList points to null
    if(inList.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.totalDiff(inList) + aminoCountHelper(this.next);
      }
      if(this.aminoAcid > inList.aminoAcid){
        return inList.totalCount() + aminoCountHelper(this.next);
      }
      return this.totalCount() + this.next.aminoAcidCompare(inList);
    }
    //If none point to null
    if(this.aminoAcid == inList.aminoAcid){
      return this.totalDiff(inList) + this.next.aminoAcidCompare(inList.next);
    }
    if(this.aminoAcid > inList.aminoAcid){
      return inList.totalCount() + this.aminoAcidCompare(inList.next);
    }
    return this.totalCount() + this.next.aminoAcidCompare(inList);

  }


  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){

    //If both list are on their last amino acid
    if(this.next == null && inList.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.codonDiff(inList);
      }
      return this.totalCount() + inList.totalCount();
    }
    //If this instance points to null
    if(this.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.codonDiff(inList) + aminoCountHelper(inList.next);
      }
      if(this.aminoAcid < inList.aminoAcid){
        return this.totalCount() + aminoCountHelper(inList.next);
      }
      return inList.totalCount() + this.codonCompare(inList.next);
    }
    //If inList points to null
    if(inList.next == null){
      if(this.aminoAcid == inList.aminoAcid){
        return this.codonDiff(inList) + aminoCountHelper(this.next);
      }
      if(this.aminoAcid > inList.aminoAcid){
        return inList.totalCount() + aminoCountHelper(this.next);
      }
      return this.totalCount() + this.next.codonCompare(inList);
    }
    //If none point to null
    if(this.aminoAcid == inList.aminoAcid){
      return this.codonDiff(inList) + this.next.codonCompare(inList.next);
    }
    if(this.aminoAcid > inList.aminoAcid){
      return inList.totalCount() + this.codonCompare(inList.next);
    }
    return this.totalCount() + this.next.codonCompare(inList);

  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if(next == null){
      return new char[]{this.aminoAcid};
    }
    char[] rec = this.next.aminoAcidList();
    char[] a = new char[rec.length + 1];
    a[0]= this.aminoAcid;
    for(int i = 0; i < rec.length; i++){

      a[i+1] = rec[i];
    }
    return a;
  }


  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    if(next == null){
      return new int[]{this.totalCount()};
    }
    int[] rec = this.next.aminoAcidCounts();
    int[] a = new int[rec.length + 1];
    a[0]= this.totalCount();
    for(int i = 0; i < rec.length; i++){

      a[i+1] = rec[i];
    }
    return a;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if(next == null) {
      return true;
    }
    if(this.aminoAcid < next.aminoAcid ){
      return next.isSorted();
    }
    return false;

  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){

    AminoAcidLL head = null;
    int aminoNumber = inSequence.length() / 3;

    for(int i = 0; i < aminoNumber; i++){
      String nextCodon = inSequence.substring(0,3);
      if(nextCodon.equals("UGA") || nextCodon.equals("UAG") || nextCodon.equals("USA")){
        i = aminoNumber;
      }
      else if(i == 0 ){
        head = new AminoAcidLL(nextCodon);
        inSequence = inSequence.substring(3);
      }
      else{
        head.addCodon(nextCodon);
        inSequence = inSequence.substring(3);
      }
    }
    return head;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){

    // Initialize sorted linked list
    AminoAcidLL sortedAminoList = null;
    AminoAcidLL head = inList;
    // Traverse the given linked list and insert every node in a sorted position
    while (head != null) {
      AminoAcidLL next = head.next;
      //Case in which it needs to be inserted at the beginning
      if (sortedAminoList == null || sortedAminoList.aminoAcid >= head.aminoAcid)
      {
        head.next = sortedAminoList;
        sortedAminoList = head;
      }
      else
      {
        head = sortedAminoList;
        /* Locate the node before the point of insertion */
        while (head.next != null && head.next.aminoAcid < head.aminoAcid){
          head = head.next;
        }
        head.next = head;
      }
      // Update current
      head = next;
    }
    // Update head_ref to point to sorted linked list
    return sortedAminoList;


    /*
    //Creates an array from the list
    AminoAcidLL copy = inList;
    AminoAcidLL[] array = new AminoAcidLL[inList.nodeCounter()];
    array[0] = copy;
    for(int i = 1; i < array.length; i++){
      copy = copy.next;
      array[i] = copy;
    }

    // Selection Sort
    for (int i = 0; i < array.length-1; i++) {
      // Find the minimum element in unsorted array
      int min = i;
      for (int j = i + 1; j < array.length; j++){
        if (array[j].aminoAcid < array[min].aminoAcid){
          min = j;
        }
      }
      // Swap the elements
      AminoAcidLL temp = array[min];
      array[min] = array[i];
      array[i] = temp;
    }

    //Connects the array back into a list
    for(int i = 0; i < array.length-1;i++){
      array[i].next = array[i+1];
    }
    array[array.length - 1].next = null;
    head = array[0];

    return head;


     */
  }


  /********************************************************************************************/
  /* increases the number of counters depending on the codon*/
  private void counterIncrease(String codon){

    for(int i = 0; i < this.counts.length;i++){
      if(this.codons[i].equals(codon)){
        this.counts[i]++;
      }
    }
  }


  /********************************************************************************************/
  /* Counts the number of Amino Acids in the list*/
  private int nodeCounter(){

    if(next == null){
      return 1;
    }
    return 1 + this.next.nodeCounter();
  }


  /********************************************************************************************/
  /*prints the number of Amino Acids in the list*/
  public void printInformation(){
    char[] a = aminoAcidList();
    int[] b = aminoAcidCounts();

    for(int i = 0; i < a.length; i++){
      System.out.print(a[i] + " ");
    }
    System.out.println();
    for(int i = 0; i < b.length; i++){
      System.out.print(b[i] + " ");
    }
    System.out.println();
  }

  /********************************************************************************************/
  /*Checks whether an amino acid has happened before(Helper for the amino acid compare*/
  public int aminoCountHelper(AminoAcidLL inList) {

    AminoAcidLL head = inList;
    int counter = 0;
    while(head != null){
      counter += head.totalCount();
      head = head.next;
    }
    return counter;

  }

  void insertAmino(AminoAcidLL newnode,AminoAcidLL sortedAminoList)
  {
    /* Special case for the head end */
    if (sortedAminoList == null || sortedAminoList.aminoAcid >= newnode.aminoAcid)
    {
      newnode.next = sortedAminoList;
      sortedAminoList = newnode;
    }
    else
    {
      AminoAcidLL current = sortedAminoList;
      /* Locate the node before the point of insertion */
      while (current.next != null && current.next.aminoAcid < newnode.aminoAcid)
      {
        current = current.next;
      }
      newnode.next = current.next;
      current.next = newnode;
    }
  }

}