# Olympic Games Administration

This simplified version of an administration and archiving system for the Olympic Games with basic functionalities was implemented as a result of a programming exam at the end of my first semester.

## Commands

To use this program via command line, you can use the following commands. Note that <> is used in the following examples to clarify the format of the command. These characters aren't used while operating.

### add-admin

The add-admin command adds a new user to the system who can manage and archive entries. This operation can be performed only if no user is logged in using the login-admin command.

`add-admin <first name>;<last name>;<user name>;<password>`

### login-admin

The login-admin command authenticates a user of the administration and archiving system and grants the user the possibility to call certain commands according to the stored rights. Only one one user can be logged in at a time. Before a new user can be successfully authenticated with the login-admin command the previously logged in user must log out using the logout-admin command.

`login-admin <user name>;<password>`

### logout-admin

The logout-admin command logs out a user of the management and archiving system who was previously authenticated with the login-in command and deprives them of the possibility to call the corresponding commands described below until the user is authenticated again.

`logout-admin`

### add-sports-venue

The add-sports-venue command adds a new sports venue to the system, which can be used as a venue for the competitions as well as a venue for the opening and closing ceremonies of the Olympic Games.

`add-sports-venue <ID>;<country name>;<location>;<name>;<opening year>;<amount of seats>`

### list-sports-venues

The list-sports-venues command lists sports facilities in each country according to their resources.

`list-sports-venues <country name>`

### add-olympic-sport

The add-olympic-sport command is used by the logged-in user to create sports and sports disciplines, in which the athletes can participate in competitions.

`add-olympic-sport <sport>;<sports discipline>`

### list-olympic-sports

The list-olympic-sports command lists the sports and disciplines alphabetically.

`list-olympic-sports`

### add-ioc-code

The add-ioc-code command creates an IOC (International Olympic Committee) country code. The input <IOC_Code> represents a three-character and unique lowercase country code. The input <country name> represents the name of the country as a string without line break and without semicolon as well as <definition year> a four-digit number as a positive integer specifying the year used to define the abbreviation specifies. All entries are uniquely identified by a three-digit <IOC_ID>. It can be assumed that an IOC code that has been created cannot or must not be changed and that a country can receive only one IOC code.

`add-ioc-code <IOC_ID>;<IOC_Code>;<country name>;<definition year>`

### list-ioc-codes

The list-ioc-codes command lists all countries with their country codes.

`list-ioc-codes`

### add-athlete

The add-athlete command adds a profile of an athlete to the management and archiving system.

`add-athlete <ID>;<first name>;<last name>;<country name>;<sport>;<sports discipline>`

### summary-athletes

The summary-athletes command lists the participants of the Olympic Games according to their sporting successes in the respective sport and sport discipline.

`summary-athletes <sport>;<sports discipline>`

### add-competition

The add-competition command adds the result of a competition of a participating athlete to the system.

`add-competition <ID>;<year>;<country name>;<sport>;<sports discipline>;<gold>;<silver>;<bronze>`

### olympic-medal-table

The olympic-medal-table command displays for all competitions of the Olympic Games a ranking list in the form of a perpetual medal table.

`olympic-medal-table`

### reset

The reset command reinitializes the Olympic Games. After a reset command, all registered users are retained. The currently logged in user will not be logged out by this.

`reset`

### quit

The quit command terminates the program.

`quit`
