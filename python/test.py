class Shark:
	
	def swim(self,value):
		print("The shark is swimming %s"%value)
		dop = Dolphin(2)

	def be_awesome(self):
		print("The shark is being awesome.")

	class Dolphin:
		def __init__(self, d):
			print(self.d)


ssh = Shark()
ssh.swim(20)