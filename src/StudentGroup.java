import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
        private int size=0;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
           
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
			if(this.students.length<1|| size<1)
				return null;
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		if(students==null) throw new IllegalArgumentException();
                this.students=students;
                size=students.length;
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if(index>=this.students.length||index<0) throw new IllegalArgumentException();
		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
                if(index>=this.students.length||index<0) throw new IllegalArgumentException();
                this.students[index]=student;
                return;
	}
        
        
	@Override
	public void addFirst(Student student) {
           if(student==null) throw new IllegalArgumentException();
		makeCapacity(size+1);
                System.arraycopy(students, 0, students, 1, size);
                students[0]=student;
                size++;
	}

	@Override
	public void addLast(Student student) {
            if(student==null) throw new IllegalArgumentException();
	    makeCapacity(size+1);
            students[size]=student;
            size++;
            return;
        }

	@Override
	public void add(Student student, int index) {
            if(student==null) throw new IllegalArgumentException();
		// Add your implementation here
                if(index>=size||index<0) throw new IllegalArgumentException();
                if(index==0) addFirst(student);
                else{
                    makeCapacity(size+1);
                    System.arraycopy(students, index, students, index+1, size-index);
                    students[index]=student;
                    size++; 
                }
                return;
	}

	@Override
	public void remove(int index) {
		// Add your implementation here
                if(index>=size||index<0) throw new IllegalArgumentException();
                System.arraycopy(students, index+1, students, index, size-index-1);
                size--;
                students=Arrays.copyOfRange(students, 0, size);
                return;
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
                if(student==null) throw new IllegalArgumentException();
                int index=getStudentIndex(student);
                if(index<0) throw new IllegalArgumentException("Student not exist");
                Student[] temp=new Student[size-1];
                int j=0;
                for(int k=0;k<index;k++) temp[j++]=students[k];
                for(int k=index+1;k<size;k++) temp[j++]=students[k];
               // System.arraycopy(students, index+1, students, index, size-index+1);
                students=temp;
                size--;
	}

	@Override
	public void removeFromIndex(int index) {
		// Add your implementation here
                if(index>=size||index<0) throw new IllegalArgumentException();
                size=index+1;
                // Student[] temp=new Student[index+1];
            students=  Arrays.copyOfRange(students, 0, index+1);
                //for(int i=size;i<students.length;i++) students[i]=null;
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here
                if(student==null) throw new IllegalArgumentException();
                 int index=getStudentIndex(student);
                 removeFromIndex(index);
                
	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
                if(index>=size||index<0) throw new IllegalArgumentException();
                Student[] temp= new Student[size-index+1];
                System.arraycopy(students, index, temp, 0, size-index);
                size=size-index+1;
                students=temp;
                return;
	}

	@Override
	public void removeToElement(Student student) {
		// Add your implementation here
                if(student==null) throw new IllegalArgumentException();
                 int index=getStudentIndex(student);
                 removeToIndex(index);
	}

	@Override
	public void bubbleSort() {
		// Add your implementation here
        /*        int n = size;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (students[j].getFullName().compareTo(students[j+1].getFullName())>0)
                {
                    // swap temp and arr[i]
                    Student temp = students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }*/
                sort2(students,0,students.length-1);
	}
	private int partition(Student arr[], int low, int high)
    {
       Student pivot = arr[high]; 
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
          if (students[j].getFullName().compareTo(pivot.getFullName())<0)
            {
                i++;

                // swap arr[i] and arr[j]
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
       Student temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    void sort2(Student arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort2(arr, low, pi-1);
            sort2(arr, pi+1, high);
        }
    }



	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if(date==null)throw new IllegalArgumentException();
 
                Student [] temp= new Student[size];
                int index=0;
                for(int i=0;i<size;i++) if(date.compareTo(students[i].getBirthDate())==0) temp[index++]=students[i];
                if(index==0)return null;
                return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if(firstDate==null || lastDate==null)throw new IllegalArgumentException();
                    
                Student [] temp= new Student[size];
                int index=0;
                for(int i=0;i<size;i++) if(students[i].getBirthDate().compareTo(firstDate)>=0&&students[i].getBirthDate().compareTo(lastDate)<=0) temp[index++]=students[i];
                if(index==0)return null;
                return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
             if(date==null) throw new IllegalArgumentException();
             if(days<0) days=0;
             Date newDate=addDays(date,days);
		return getBetweenBirthDates(date,newDate);
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
                indexOfStudent--;
                if(indexOfStudent<0) throw new IllegalArgumentException();
                
		return getAge(students[indexOfStudent].getBirthDate());
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		if(age<0) return null;
                Student[] temp= new Student[size];
                int index=0;
                for(int i=0;i<size;i++)
                    if(getAge(students[i].getBirthDate())==age) temp[index++]=students[i];
                if(index==0) return null;
                return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		if(size==0)
		return null;
		int index=0;
		double marks=-99999.0;
		for(int i=0;i<size;i++)
			if(Double.compare(students[i].getAvgMark(),marks)>0) marks=students[i].getAvgMark();
		Student[] temp=new Student[size];
		for(int i=0;i<size;i++) if(Double.compare(students[i].getAvgMark(),marks)==0) temp[index++]=students[i];
		if(index==0) return null;
		return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		if(student==null) throw new IllegalArgumentException();
		int index=getStudentIndex(student);
		if(index==size-1||index==-1)
		return null;
		return students[index+1];
	}
        
        
        public void makeCapacity(int requiredCapacity){
                int oldLength=students.length;
              
                if(requiredCapacity>oldLength){   
                 // int   capacity=oldLength+(oldLength>>1);//Inspired from Arrylist
                 int capacity=oldLength+1;
                  if(capacity<requiredCapacity)
                        capacity=requiredCapacity;
                  Student temp[]= students;
              //    System.arraycopy(temp,, size, temp, size, oldLength);
                  students = Arrays.copyOf(temp, capacity);
                }
                
        
        }
        private Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
        private int getStudentIndex(Student student){
            for(int i=0;i<size;i++){
                if(students[i].compareTo(student)==0) return i;
            }
            return -1;
        }

   	 private int getAge(Date dateOfBirth) { //Thanks to StackOverflow
		 int year=dateOfBirth.getYear(),month=dateOfBirth.getMonth(),day=dateOfBirth.getDay();	
		 Date now = new Date();
	   	 int nowMonth = now.getMonth()+1;
	   	 int nowYear = now.getYear()+1900;
	   	 int result = nowYear - year;

	  	  if (month > nowMonth) {
			result--;
		    }
		   else if (month == nowMonth) {
			int nowDay = now.getDate();

			if (day > nowDay) {
			    result--;
			}
		    }
	   	 return result;

	}

}
