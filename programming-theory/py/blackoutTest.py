'''
Created on Feb 11, 2014

@author: robertoflores
'''
import unittest
import blackout

class Test(unittest.TestCase):
    def test0(self):
        content = "Dear friend," \
        "I just wanted to let you know that I am alive and well. Sherman and Mr Peabody are " \
        "well also. I'm glad we were able to retire. My only complaint " \
        "is that I wish we could have found someplace more exciting than " \
        "Kansas for us to live in! If you really need to contact us, you can " \
        "do so by telephone. The number is (757) 555-0478, but don't tell anyone." \
        "Later," \
        "Rocky the flying squirrel" 
        words    = [ "Rocky", "bullwinkle", "peabody", "Kansas", "irs", "medicare", "phone", "555" ]
        actual   = blackout.censorContent( content, words )
        expected = "Dear friend," \
        "I just wanted to let you know that I am alive and well.@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@ I'm glad we were able to retire.@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@ If you really need to contact us, you can " \
        "do so by telephone.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@" 
        self.maxDiff = None
        self.assertEqual( expected, actual )
        pass   
    
    def test1(self):
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing " \
        "elit. Cras elorem nibh ut lacus dapibus rutrum? Cras augue mi, " \
        "loremus ut aliquet vitae, faucibus vitae lacus. Loremi " \
        "convallis commodo mi, vitae scelerisque tellus iaculis non. " \
        "Morbi in convallis felis, vitae lorem risus! Nunc id dui " \
        "eu risus mattis consectetur id ac diam. Curabitur diam sem, " \
        "elorem suscipit turpis quis, elementum dignissim est. Lorem"
        words    = [ "Lorem" ]
        actual   = blackout.censorContent( content, words )
        expected = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@ Cras elorem nibh ut lacus dapibus rutrum? Cras augue mi, " \
        "loremus ut aliquet vitae, faucibus vitae lacus. Loremi " \
        "convallis commodo mi, vitae scelerisque tellus iaculis non.@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Nunc id dui " \
        "eu risus mattis consectetur id ac diam. Curabitur diam sem, " \
        "elorem suscipit turpis quis, elementum dignissim est.@@@@@@" 
        self.maxDiff = None
        self.assertEqual( expected, actual )
        pass

    def test2(self):
        content = "All staff who deal with sources are accredited journalists. " \
        "All submissions establish a journalist-source relationship. Online " \
        "submissions are routed via Sweden and Belgium. These countries have " \
        "first rate journalist-source shield laws. Sweden law provide protection " \
        "against any official inquiry into journalists' sources. It also allows " \
        "a source whose identity has been revealed without permission to initiate " \
        "criminal prosecutions against a journalist who has breached his or her " \
        "promise of confidentiality. WikiLeaks records no source identifying " \
        "information. It has a number of submission mechanisms available to deal " \
        "with even the most sensitive national security information. WikiLeaks " \
        "is the winner of the 2008 Economist Index on Censorship Freedom of " \
        "Expression award. It also won the 2009 Amnesty International human " \
        "rights reporting award. WikiLeaks has a history breaking major stories " \
        "in every major media outlet and robustly protecting sources and press " \
        "freedoms. No source has ever been exposed and no material has ever been " \
        "censored. Since formation in early 2007, WikiLeaks has been victorious " \
        "over every legal (and illegal) attack, including those from the Pentagon " \
        "and the Chinese Public Security Bureau. Others include the Former " \
        "president of Kenya, the Premier of Bermuda, Scientology, the Catholic & " \
        "Mormon Church, the largest Swiss private bank, and Russian companies. " \
        "WikiLeaks has released more classified intelligence documents than the " \
        "rest of the world press combined."
        words    = [ "wIKiLeAKs", "journalist" ]
        actual   = blackout.censorContent( content, words )
        expected = "All staff who deal with sources are accredited journalists.@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Online " \
        "submissions are routed via Sweden and Belgium.@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Sweden law provide protection " \
        "against any official inquiry into journalists' sources.@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@ It has a number of submission mechanisms available to deal " \
        "with even the most sensitive national security information.@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@ It also won the 2009 Amnesty International human " \
        "rights reporting award.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@ No source has ever been exposed and no material has ever been " \
        "censored.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Others include the Former " \
        "president of Kenya, the Premier of Bermuda, Scientology, the Catholic & " \
        "Mormon Church, the largest Swiss private bank, and Russian companies.@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
        self.maxDiff = None
        self.assertEqual( expected, actual )
        pass

    def test3(self):
        content = "Example WikiLeak Reports."
        "* Changes in Guantanamo Bay SOP manual. From 2003-2004. Guantanamo Bay's main operations manuals." \
        "* Of Orwell, Wikipedia and Guantanamo Bay. In where we track down and expose Guantanamo Bay's" \
        "propaganda team. * Como entrenar a escuadrones de la muerte y aplastar revoluciones de El Salvador" \
        "a Iraq. By the US Special Forces manual. How to prop up unpopular government with paramilitaries." \
        "* Secret gag on UK Times preventing publication of Minton report into toxic waste dumping." \
        "Dated 16 Sep 2009. Publication of variations of a so-called super-injunction, one of many gag-orders" \
        "published by WikiLeaks. It exposes successful attempts to suppress the free press via repressive legal" \
        "attacks. * How German intelligence infiltrated Focus magazine. Illegal spying on German journalists." \
        "* US Intelligence planned to destroy WikiLeaks. Dated 18 Mar 2008. Classified (SECRET/NOFORN) 32" \
        "page US counterintelligence investigation into WikiLeaks. It has been in the worldwide news." \
        "* Tehran Warns US Forces against Chasing Suspects into Iran. Iran warns the United States over" \
        "classified document on WikiLeaks. Climatic Research Unit emails, data, models. Dated 1996-" \
        "2009. Over 60MB of emails, documents, code and models from the Climatic Research Unit at the" \
        "University of East Anglia, written between 1996 and 2009 that lead to a worldwide debate." \
        "* Barclays Bank gags Guardian over leaked memos detailing offshore tax scam. Dated 16 Mar, 2009." \
        "Publication of censored documents revealing a number of elaborate international tax avoidance schemes" \
        "by the SCM (Structured Capital Markets) division of Barclays. * Whistleblower exposes insider" \
        "trading program at JP Morgan. Legal insider trading in three easy steps, brought to you by JP" \
        "Morgan and the SEC. * Church of Scientology's 'Operating Thetan' documents leaked online." \
        "Scientology's secret, and highly litigated bibles. * Report on Shriners raises question of" \
        "wrongdoing. Corruption exposed at 22 US and Canadian children's hospitals. * Texas Catholic" \
        "hospitals did not follow Catholic ethics, report claims."
        words    = [ "guantanamo", "US", "morgan", "Bank" ]
        actual   = blackout.censorContent( content, words )
        expected = "Example WikiLeak Reports."
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ From 2003-2004.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@ * Como entrenar a escuadrones de la muerte y aplastar revoluciones de El Salvador" \
        "a Iraq.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ How to prop up unpopular government with paramilitaries." \
        "* Secret gag on UK Times preventing publication of Minton report into toxic waste dumping." \
        "Dated 16 Sep 2009. Publication of variations of a so-called super-injunction, one of many gag-orders" \
        "published by WikiLeaks. It exposes successful attempts to suppress the free press via repressive legal" \
        "attacks. * How German intelligence infiltrated Focus magazine. Illegal spying on German journalists." \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Dated 18 Mar 2008.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ It has been in the worldwide news." \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Iran warns the United States over" \
        "classified document on WikiLeaks. Climatic Research Unit emails, data, models. Dated 1996-" \
        "2009. Over 60MB of emails, documents, code and models from the Climatic Research Unit at the" \
        "University of East Anglia, written between 1996 and 2009 that lead to a worldwide debate." \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Dated 16 Mar, 2009." \
        "Publication of censored documents revealing a number of elaborate international tax avoidance schemes" \
        "by the SCM (Structured Capital Markets) division of Barclays.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" \
        "@@@@@@@@@@@@@@@@@@@ * Church of Scientology's 'Operating Thetan' documents leaked online." \
        "Scientology's secret, and highly litigated bibles. * Report on Shriners raises question of" \
        "wrongdoing.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ * Texas Catholic" \
        "hospitals did not follow Catholic ethics, report claims."
        self.maxDiff = None
        self.assertEqual( expected, actual )
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test0']
    unittest.main()