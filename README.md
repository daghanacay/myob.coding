# Build

Assuming that you have unzipped the code or pulled from github

- Go to the folder that the application code lives and on command line execute 
	$./gradlew bootRepackage

# Run

Executable is deployed to build/libs folder and can be executed as follows
    $ java -jar myob.coding-0.0.1-SNAPSHOT.jar {input file name} {output file name}
    
# Assumptions

1. Input file rows are structured as follows:
 
first name, last name,annual salary,	super rate%, payment start date.
Eg. David,Rudd,60050,9%,01 March-31 March
 
2. Start date is after 1st July 2012
3. Start date is valid, e.g. 
..* it is a valid month, 
..* a valid last day of the month
..* no more than 1 month is covered in the range