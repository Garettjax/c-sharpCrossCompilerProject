using System;
namespace Loops
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            int test1 = 1;
            while (test1 <= 4)
            {
                Console.WriteLine("Test 1 Passed (prints 4 times)");
                test1++;
            }
            
            for (int test2 = 1; test2 <= 4; test2++)
                Console.WriteLine("Test 2 Passed (should print 4 times)");

            int test3 = 21;
            do
            {
                Console.WriteLine("If this line prints once, Test 3 Passed");
                test3++;
            } while (test3 < 20);
            
            for(int i = 2; i < 3; i++) 
            for(int j = 1; j < i; j++) 
                Console.WriteLine("Test 4 Passed if prints once"); 
        } 
    }
}