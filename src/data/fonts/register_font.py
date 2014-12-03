"""

This simple script is a tool that generates Fonts.java. 
It should be run everytime a new font is added to the 
fonts/ directory. It makes adding fonts to the library
as simple as typing "python register_font.py"

"""

import os

fonts_file = open("../Fonts.java", 'w')

# Writes the default header - ie the stuff that isn't
# dependent on what is in the fonts/ directory

fonts_file.write(
'package data;\n\
\n\
import java.awt.*; \n\
import java.io.IOException; \n\
\n\
///////////////////////////////////\n\
// GENERATED CODE - DO NOT ALTER //\n\
///////////////////////////////////\n\n\
public class Fonts {\n\
\n\
    public final Font TITLE = new Font("Verdana", Font.PLAIN, 36);\n\
    public final Font DEFAULT = new Font("Verdana", Font.PLAIN, 24);\n\
    public final Font SMALL = new Font("Verdana", Font.PLAIN, 14);\n\
\n\
    public final Font TITLE_BOLD = new Font("Verdana", Font.BOLD, 36);\n\
    public final Font DEFAULT_BOLD = new Font("Verdana", Font.BOLD, 24);\n\
    public final Font SMALL_BOLD = new Font("Verdana", Font.BOLD, 14);\n\
\n\
    public Font BUTTON_FONT = new Font("Verdana", Font.PLAIN, 24);\n\
\n\
	public Fonts() {\n\
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();\n')

# Gets all the fonts in the fonts/ directory
font_dirs = [x[0] for x in os.walk(os.getcwd())]
font_dirs = font_dirs[1:]

# Writes the code to add a font to the register
for font_dir in font_dirs:
	font_family = font_dir.split("\\")[-1].capitalize()
	fonts_file.write(
"\n\
		// Registers " + font_family + " Fonts\n\
		try {\n")

	fonts = [each for each in os.listdir(font_dir) if each.endswith('.ttf')]
	for font in fonts:
		fonts_file.write('\t\t\tge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/' + font_family.lower() + '/' + font  + '")));\n')

	fonts_file.write(
"		} catch (FontFormatException e) {\n\
     	   e.printStackTrace();\n\
    	} catch (IOException e) {\n\
     	   e.printStackTrace();\n\
    	}\n")

fonts_file.write("\t}\n}")
