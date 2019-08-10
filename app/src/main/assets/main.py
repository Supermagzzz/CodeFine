sep = ["(", ")", "+", "-", "*", "\\", "%", "[", "]", " ", ";", ",", "<", ">", "."]
blue = ["int", "long", "byte", "bool", "if", "for", "else", "char", "while", "&&", "||"]
red = ["return", "struct", "!"]
green = ["vector", "cout", "cin", "endl", "reverse", "sort", "max", "min", "max_element", "min_element", "cout.precision", "fixed", "string", "swap", "push_back", "size", "begin", "end", "unique", "resize"]


output = open('code.txt', 'w')
inputf = open('input.txt', 'r')
codeAll = inputf.read().split('\n')
output.write("		</p>\n")
output.write("			<div class='code_block_b'>\n")
output.write("  			<code>\n")
count = 0
for code in codeAll:
	code = code.strip()
	curCount = code.count('{') - code.count('}')
	if curCount < 0:
		count += curCount

	output.write("				" + "<t></t>" * count)
	
	if code.startswith("#include"):
		code = code.replace('<', "&lt;")
		code = code.replace('>', "&gt;")
		code = "<gr>" + code + "</gr>"
		output.write(code)
	elif code == "using namespace std;":
		code = "<bl>" + code + "</bl>"
		output.write(code)
	else:
		tmp_code = code[:]
		code = [""]
		for j in tmp_code:
			if j in sep:
				code.append(j)
			else:
				if code[-1] in sep:
					code.append("")
				code[-1] += j
		res = ""
		doGreen = False
		for word in code:
			if len(word) > 0 and word[0] == "\"":
				doGreen = True
				res += "<gr>"
			if doGreen:
				word = word.replace('<', "&lt;").replace('>', "&gt;")
				res += word
			elif word in blue:
				word = word.replace('<', "&lt;").replace('>', "&gt;")
				res += "<bl>" + word + "</bl>"
			elif word in red:
				word = word.replace('<', "&lt;").replace('>', "&gt;")
				res += "<re>" + word + "</re>"
			elif word in green:
				word = word.replace('<', "&lt;").replace('>', "&gt;")
				res += "<gr>" + word + "</gr>"
			else:
				word = word.replace('<', "&lt;").replace('>', "&gt;")
				flag = len(word) != 0
				for i in word:
					if ord('0') > ord(i) or ord(i) > ord('9'):
						flag = False
						break
				if flag:
					res += "<pu>" + word + "</pu>"	
				else:
					res += word
			if len(word) > 0 and word[-1] == "\"":
				doGreen = False
				res += "</gr>"

		res = res.replace(" &lt;&lt; ", " <re>&lt;&lt;</re> ").replace(" &gt;&gt; ", " <re>&gt;&gt;</re> ")

		output.write(res)

	
	if curCount > 0:
		count += curCount
	output.write("<br>\n")
output.write("			  </code>\n")
output.write("			</div>\n")
output.write("		<p>\n")
