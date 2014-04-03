# Juan Vallejo 100%
import re
message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras elorem nibh ut lacus dapibus rutrum? Cras augue mi, loremus ut aliquet vitae, faucibus vitae lacus. Loremi convallis commodo mi, vitae scelerisque tellus iaculis non. Morbi in convallis felis, vitae lorem risus! Nunc id dui eu risus mattis consectetur id ac diam. Curabitur diam sem, elorem suscipit turpis quis, elementum dignissim est. Lorem"
content = ['Lorem']
# message = "Dear friend, I just wanted to let you know that I am alive and well. Sherman and Mr Peabody are well also. I'm glad we were able to retire. My only complaint is that I wish we could have found someplace more exciting than Kansas for us to live in! If you really need to contact us, you can do so by telephone. The number is (757) 555-0478, but don't tell anyone. Later, Rocky the flying squirrel"
# content = ['Rocky','Peabody','Kansas','Bullwinkle','irs','medicare','555','phone']
def censorContent(a,b):
	i = 0
	di = -1
	ans = []
	for c in a:
		if c == "." or c == "?" or c == "!":
			add = False
			cns = False
			sen = a[di+1:i+1]
			h = 0
			dh = -1
			for d in sen:
				m = re.match("([^a-z0-9\'])",d,re.I|re.M)
				if(m):
					word = sen[dh+1:h]
					dh = h
					if not cns:
						for y in b:
							if word.lower() == y.lower():
								cns = True
								csen = ""
								for n in sen:
									csen += "@"
								ans.append(csen)
				h+=1
			di = i
			if(not cns):
				ans.append(sen)
		i+=1
	cns = False
	lsen = a[di+1:len(a)+1]
	scase = lsen.split(" ")[1] if(re.match(" [a-z0-9]",lsen,re.I|re.M)) else False
	j = 0
	dj = -1
	for p in lsen:
		m = re.match("([^a-z0-9\'](\n)?)",p,re.I|re.M)
		if(m or (j == len(lsen)-1)):
			word = lsen[dj+1:j] if not scase else lsen[dj+1:j+1]
			dj = j
			if not cns:
				for x in b:
					if word.lower() == x.lower():
						cns = True
						clsen = ""
						for n in lsen:
							clsen += "@"
						ans.append(clsen)
		j+=1
	if not cns:
		ans.append(lsen)
	return ''.join(ans)
print(censorContent(message,content))