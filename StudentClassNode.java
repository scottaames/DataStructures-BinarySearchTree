package p7_package;

/**
 * Description: class manages data for a student, including name, student ID, 
 * gender, and gpa
 *  
 *  @author Scott Ames
 * 
 */
public class StudentClassNode
   {
    /**
     * member data - name
     */
    public String name;

    /**
     * member data - student ID
     */
    public int studentID;
    
    /**
     * member data - gender
     */
    public char gender;
    
    /**
     * member data - gpa
     */
    public double gpa;
    
    /**
     * member data - left child reference
     */
    public StudentClassNode leftChildRef;
    
    /**
     * member data - right child reference
     */
    public StudentClassNode rightChildRef;
    
    /** 
     * Default constructor, initializes all student data to default values
     */
    public StudentClassNode()
       {
        name = "---";
        studentID = 0;
        gender = '-';
        gpa = 0.000;
        
        leftChildRef = rightChildRef = null;
       }
      
    /** 
     * Initialization constructor, individually sets data values
     * 
     * @param nameIn String name of student
     * 
     * @param studentIDIn integer student ID number
     * 
     * @param genderIn char gender of student
     * 
     * @param gpaIn double gpa of student
     */
    public StudentClassNode( String nameIn, int studentIDIn, 
                                                char genderIn, double gpaIn )
       {
        name = nameIn;
        studentID = studentIDIn;
        gender = genderIn;
        gpa = gpaIn;
        
        leftChildRef = rightChildRef = null;
       }
      
    /** 
     * Copy constructor, sets all data to copied object
     * 
     * @param copied StudentClass object to be copied
     */
    public StudentClassNode( StudentClassNode copied )
       {
        name = copied.name;
        studentID = copied.studentID;
        gender = copied.gender;
        gpa = copied.gpa;
        
        leftChildRef = copied.leftChildRef;
        rightChildRef = copied.rightChildRef;
       }
      
    /**
     * Sets only the data from another StudentClassNode object
     * to THIS object
     * 
     * @param newData StudentClassNode object with data
     * to be set in THIS object
     */
    public void setStudentClassData( StudentClassNode newData )
       {
        name = newData.name;
        studentID = newData.studentID;
        gender = newData.gender;
        gpa = newData.gpa;
       }
    
    /** 
     * Overrides Object.toString, provides raw data from object
     */
    @Override
    public String toString()
       {
        return name + '/' + studentID + '/' + gender + '/' + gpa;
       }    
   }

    