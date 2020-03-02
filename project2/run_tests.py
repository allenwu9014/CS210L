import datetime, os, signal, subprocess, sys, time, unittest

def run(command, stdin = None, timeout = 30):
    """
    Runs the specified command using specified standard input (if any) and
    returns the output on success. If the command doesn't return within the
    specified time (in seconds), "__TIMEOUT__" is returned.
    """

    start = datetime.datetime.now()
    process = subprocess.Popen(command.split(),
                               stdin = subprocess.PIPE, 
                               stdout = subprocess.PIPE,
                               stderr = subprocess.STDOUT)
    if not stdin is None:
        process.stdin.write(bytes(stdin, 'utf-8'))
    process.stdin.close()
    while process.poll() is None:
        time.sleep(0.1)
        now = datetime.datetime.now()
        if (now - start).seconds > timeout:
            os.kill(process.pid, signal.SIGKILL)
            os.waitpid(-1, os.WNOHANG)
            return "__TIMEOUT__"
    result = process.stdout.read().decode("utf-8")
    process.stdout.close()
    return result

class Exercise1(unittest.TestCase):
    
    def test1(self):
        command = "java BinaryStrings 4"
        sought = """0000
0001
0010
0011
0100
0101
0110
0111
1000
1001
1010
1011
1100
1101
1110
1111
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)
    
class Exercise2(unittest.TestCase):
    
    def test1(self):
        command = "java Primes 10"
        sought = """2
3
5
7
11
13
17
19
23
29
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

class Exercise3(unittest.TestCase):
    
    def test1(self):
        command = """java MinMax"""
        sought = """true
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

class Exercise4(unittest.TestCase):
    
    def test1(self):
        command = "java Buffer"
        sought = """|There is grandeur in this view of life, with its several powers, having been originally breathed by the Creator into a few forms or into one; and that, whilst this planet has gone cycling on according to the fixed law of gravity, from so simple a beginning endless forms most beautiful and most wonderful have been, and are being, evolved. -- Charles Darwin, The Origin of Species
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

class Exercise5(unittest.TestCase):
    
    def test1(self):
        command = "java Josephus 7 2"
        sought = """2
4
6
1
5
3
7
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

    def test2(self):
        command = "java Josephus 20 3"
        sought = """3
6
9
12
15
18
1
5
10
14
19
4
11
17
7
16
8
2
13
20
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)
        
class Problem1(unittest.TestCase):
    
    def test1(self):
        command = "java LinkedDeque"
        sought = """false
(364 characters) There is grandeur in this view of life, with its several powers, having been originally breathed into a few forms or into one; and that, whilst this planet has gone cycling on according to the fixed law of gravity, from so simple a beginning endless forms most beautiful and most wonderful have been, and are being, evolved. ~ Charles Darwin, The Origin of Species
true
"""
        got = run(command)
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

class Problem2(unittest.TestCase):
    
    def test1(self):
        command = "java ResizingArrayRandomQueue"
        sought = """55
0
55
true
"""
        got = run(command, " ".join([str(i) for i in range(11)]))
        self.assertNotEqual(got, "__TIMEOUT__")
        self.assertEqual(got, sought)

class Problem3(unittest.TestCase):
    
    def test1(self):
        command = "java Subset 3"
        got = run(command, "A B C D E F G H I")
        self.assertNotEqual(got, "__TIMEOUT__")
        a, b = got.split(), "A B C D E F G H I".split()
        self.assertTrue(len(a) == 3 and set(a).issubset(set(b)))

    def test2(self):
        command = "java Subset 3"
        got = run(command, "A B C D E F G H I")
        self.assertNotEqual(got, "__TIMEOUT__")
        a, b = got.split(), "A B C D E F G H I".split()
        self.assertTrue(len(a) == 3 and set(a).issubset(set(b)))

    def test3(self):
        command = "java Subset 8"
        got = run(command, "AA BB BB BB BB BB CC CC")
        self.assertNotEqual(got, "__TIMEOUT__")
        a, b = got.split(), "AA BB BB BB BB BB CC CC".split()
        self.assertTrue(set(a) == set(b))
        
if __name__ == "__main__":
    unittest.main()
