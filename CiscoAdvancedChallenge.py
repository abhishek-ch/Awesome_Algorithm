__author__ = 'achoudhary'
#https://www.hackerrank.com/contests/software-challenge/challenges/smallest-string-and-regex

if __name__ == '__main__':
    length = int(raw_input())
    regex = raw_input()

    #fail case
    if length > 500 or (regex.count('*') == 0 and regex.count('|')== 0 and (len(regex) < length)):
        print(-1)





    print(regex)
