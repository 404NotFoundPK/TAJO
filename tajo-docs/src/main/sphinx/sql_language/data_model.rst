**********
Data Model
**********

==========
Data Types
==========

+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| Support   | SQL Type Name  |  Alias                     | Size (byte) | Description                                       | Range                                                                    |
+===========+================+============================+=============+===================================================+==========================================================================+ 
| O         | boolean        |  bool                      |  1          |                                                   | true/false                                                               |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+  
|           | bit            |                            |  1          |                                                   | 1/0                                                                      | 
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | varbit         |  bit varying               |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | tinyint        |  int1                      |  1          | tiny-range integer value                          | -2^7 (-128) to 2^7-1 (127)                                               |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+
| O         | smallint       |  int2                      |  2          | small-range integer value                         | -2^15 (-32,768) to 2^15-1 (32,767)                                       |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | integer        |  int, int4                 |  4          | integer value                                     | -2^31 (-2,147,483,648) to 2^31 - 1 (2,147,483,647)                       |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | bigint         |  int8                      |  8          | larger range integer value                        | -2^63 (-9,223,372,036,854,775,808) to 2^63-1 (9,223,372,036,854,775,807) |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | real           |  float4                    |  4          |                                                   | -3.4028235E+38 to 3.4028235E+38 (6 decimal digits precision)             |
+-----------+----------------+----------------------------+-------------+                                                   +--------------------------------------------------------------------------+
| O         | double         |  float8, double precision  |  8          | variable-precision, inexact, real number value    | 1 .7E???308 to 1.7E+308 (15 decimal digits precision)                      |
+-----------+----------------+----------------------------+-------------+                                                   +--------------------------------------------------------------------------+
| O         | float[(n)]     |                            |  4 or 8     |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+
|           | number         |  decimal                   |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | char[(n)]      |  character                 |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | varchar[(n)]   |  character varying         |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | text           |  text                      |             | variable-length unicode text                      |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | binary         |  binary                    |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | varbinary[(n)] |  binary varying            |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | blob           |  bytea                     |             | variable-length binary string                     |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | date           |                            |             |                                                   |                                                                          | 
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | time           |                            |             |                                                   |                                                                          | 
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | timetz         |  time with time zone       |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
| O         | timestamp      |                            |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 
|           | timestamptz    |                            |             |                                                   |                                                                          |
+-----------+----------------+----------------------------+-------------+---------------------------------------------------+--------------------------------------------------------------------------+ 

-----------------------------------------
Using real number value (real and double)
-----------------------------------------

The real and double data types are mapped to float and double of java primitives respectively. Java primitives float and double follows the IEEE 754 specification. So, these types are correctly matched to SQL standard data types.

+ float[( n )] is mapped to either float or double according to a given length n. If n is specified, it must be bewtween 1 and 53. The default value of n is 53.
+ If 1 <- n <- 24, a value is mapped to float (6 decimal digits precision).
+ If 25 <- n <- 53, a value is mapped to double (15 decimal digits precision). 
+ Do not use approximate real number columns in WHERE clause in order to compare some exact matches, especially the - and <> operators. The > or < comparisons work well. 