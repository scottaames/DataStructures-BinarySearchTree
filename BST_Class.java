package p7_package;

public class BST_Class
{
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;
    
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;
    
    /**
     * Default class constructor, initializes BST
     */
    public BST_Class()
    {
        BST_Root = null;
    }
    
    /**
     * Copy constructor
     * <p>
     * Note: Uses copyConstHelper
     * 
     * @param copied - BST_Class to be copied
     */
    public BST_Class( BST_Class copied )
    {
        BST_Root = copyConstHelper( copied.BST_Root );
    }
    
    /**
     * Copy constructor helper, recursively adds nodes to duplicate tree
     * 
     * @param copiedRef - StudentClassNode reference for accessing
     *  copied object data
     *  
     * @return StudentClassNode reference to node added at current level
     *  of recursion
     */
    private StudentClassNode copyConstHelper( StudentClassNode copiedRef )
    {
        
        StudentClassNode newNode = null;
        
        if ( copiedRef != null )
        {
           newNode = new StudentClassNode( copiedRef);
           
           newNode.leftChildRef = copyConstHelper( copiedRef.leftChildRef );
           
           newNode.rightChildRef = copyConstHelper( copiedRef.rightChildRef );
        }
        
        return newNode;
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: uses insert helper method
     * 
     * @param inData - StudentClassNode data to be added to BST
     */
    public void insert( StudentClassNode inData )
    {
        insertHelper( BST_Root, inData);
    }
    
    /**
     * Insert helper method for BST insert action
     * 
     * @param localRoot - StudentClassNode tree root reference at 
     * the current recursion level
     * 
     * @param inData - StudentClassNode item to be added to BST
     */
    private void insertHelper( StudentClassNode localRoot, 
                               StudentClassNode inData )
    {   
        // check for null
        if( !isEmpty() )
        { 
            // check if new data is less than root data
            if( inData.studentID < localRoot.studentID )
            {
               //if roots left child is null
               if( localRoot.leftChildRef == null )
               {
                  // set new data to root's left child
                  localRoot.leftChildRef = new StudentClassNode( inData );
               }
               // assume child is not null
               else
               {
                   // recurse left
                   insertHelper(localRoot.leftChildRef, inData);
               }
            }
            
           // repeat for the right side, and check if greater than
           if( inData.studentID > localRoot.studentID )
           {
               if( localRoot.rightChildRef == null )
               {
                  localRoot.rightChildRef = new StudentClassNode( inData );;
               }
               else
               {
                   insertHelper(localRoot.rightChildRef, inData);
               }
           }
        }
        //root is null and becomes the data to be inserted
        else
        {
            BST_Root = new StudentClassNode( inData );
        }
        
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * 
     * @param inData - StudentClassNode that includes the necessary key
     * 
     * @return StudentClassNode reference result of remove action
     */
    public StudentClassNode removeNode( StudentClassNode outData )
    {
        return removeNodeHelper(BST_Root, outData);
    }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: uses removeFromMin method
     * 
     * @param localRoot - StudentClassNode tree root reference at
     *  the current recursion level
     *  
     * @param outData - StudentClassNode item that includes the 
     * necessary key
     * 
     * @return StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper( StudentClassNode localRoot,
                                               StudentClassNode outData )
    {
       // check for null
       if( isEmpty() )
       {
          // set local to null
          localRoot = null;
       }
       // check for data less than node
       else if( localRoot.studentID > outData.studentID )
       {
          // recurse left
          localRoot.leftChildRef = 
                  removeNodeHelper( localRoot.leftChildRef, outData );
       }
       
       //check for data greater than node
       else if( localRoot.studentID < outData.studentID )
       {
          //recurse right
          localRoot.rightChildRef = 
                  removeNodeHelper( localRoot.rightChildRef, outData );
       }
       // check for no children
       else if( localRoot.leftChildRef == null &&
              localRoot.rightChildRef == null )
       {
           // set local to null
           localRoot = null;
       }
       
       // check for no left child
       else if ( localRoot.leftChildRef == null )
       {
          // set local right child
          localRoot = localRoot.rightChildRef;
       }
      
       // check for no right child
       else if( localRoot.rightChildRef == null )
       {
          // set local to left child
           localRoot = localRoot.leftChildRef;
       }
       
       // check if the right child has no left child
       else if( localRoot.rightChildRef.leftChildRef == null )
       {
           
           // place data from right child into local
           localRoot.setStudentClassData( outData );
           
           // link right child to right child's right child
           localRoot.rightChildRef = localRoot.rightChildRef.rightChildRef;
       }
       
       // assume right child has left child
       else
       {
           // set temp to removeFromMin
           StudentClassNode temp = removeFromMin( localRoot, localRoot.rightChildRef );
           
           // assign temp DATA to local
           localRoot.setStudentClassData( temp );
       }
       
       return localRoot;
    }
    
    /**
     * Searches tree from given node to minimum value node below it, stores
     * data value found, and then unlinks the node
     * 
     * @param minParent - StudentClassNode reference to current node
     * 
     * @param minChild- StudentClassNode reference to child node to be tested
     * 
     * @return StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMin( StudentClassNode minParent, 
                                            StudentClassNode minChild )
    {
        if( minChild.leftChildRef != null )
        {
            removeFromMin(minChild, minChild.leftChildRef);
        }
        
        minParent.leftChildRef = minChild.rightChildRef;
        
        return minChild;
    }
    
    /**
     * Searches for data in BST given StudentClassNode with necessary key
     * 
     * @param searchData - StudentClassNode item containing key
     * 
     * @return StudentClassNode reference to found data
     */
    public StudentClassNode search( StudentClassNode searchData )
    {
        return searchHelper(BST_Root, searchData);
    }
    
    /**
     * Helper method for BST search action 
     * 
     * @param localRoot - StudentClassNode tree root reference at the
     * current recursion level
     * 
     * @param searchData - StudentClassNode item containing key
     * 
     * @return StudentClassNode item found
     */
    private StudentClassNode searchHelper( StudentClassNode localRoot, 
                                           StudentClassNode searchData )
    {
       StudentClassNode dataToSearch = searchData;
       
       if( !isEmpty() )
       {
            if( dataToSearch.studentID < localRoot.studentID )
            {
                return searchHelper(localRoot.leftChildRef, dataToSearch);
            }
            
            if( dataToSearch.studentID > localRoot.studentID )
            {
               return searchHelper(localRoot.rightChildRef, dataToSearch);
            }
       }
       
       return localRoot;
    }
    
    /**
     * Provides preOrder traversal for user as a string
     * 
     * @return String containing pre-order output
     */
    public String outputPreOrder()
    {
       outputString = "";
        
       outputPreOrderHelper(BST_Root);
       
       return outputString;
    }
    
    /**
     * Provides preOrder traversal action helper
     * 
     * @param localRoot - StudentClassNode tree root reference at the
     *  current recursion level
     */
    private void outputPreOrderHelper( StudentClassNode localRoot )
    {
       if ( !isEmpty() )
       {
           outputString += localRoot.toString() + "\n";
           
           outputPreOrderHelper(localRoot.leftChildRef);
           
           outputPreOrderHelper(localRoot.rightChildRef);
       }
    }
    
    /**Provides postOrder traversal for us as a string
     * 
     * @return String containing post order output
     */
    public String outputPostOrder()
    {
        outputString = "";
        
        outputPostOrderHelper(BST_Root);
        
        return outputString;
    }
    
    /**
     * Provides postOrder traversal action helper
     * 
     * @param localRoot - StudentClassNode tree root reference at the
     * current recursion level
     */
    private void outputPostOrderHelper( StudentClassNode localRoot )
    {
       if ( !isEmpty() )
       {
           outputPostOrderHelper(localRoot.leftChildRef);
           
           outputPostOrderHelper(localRoot.rightChildRef);
           
           outputString += localRoot.toString() + "\n";
       }
    }
    
    /**
     * Provides inOrder traversal for user as a string
     * 
     * @return String containing in order output
     */
    public String outputInOrder()
    {
        outputString = "";
        
        outputInOrderHelper(BST_Root);
        
        return outputString;
    }
    
    /**
     * Provides inOrder traversal action helper
     * 
     * @param localRoot - StudentClassNode tree root reference at the
     * current recursion level
     */
    private void outputInOrderHelper( StudentClassNode localRoot )
    {
        if ( !isEmpty() )
        {
            outputInOrderHelper(localRoot.leftChildRef);
            
            outputString += localRoot.toString() + "\n";
            
            outputInOrderHelper(localRoot.rightChildRef);
        }
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = null;
    }
    
    /**
     * Test for empty tree
     * 
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return BST_Root == null;
    }
}