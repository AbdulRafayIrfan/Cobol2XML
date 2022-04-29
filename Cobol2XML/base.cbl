identification division.

program-id.  base_jb.


date-written.  7-jul-1995 - mb.

data division.
working-storage section.

procedure division.

call "c$justify" using entry_char "R".

call "c$toupper" using entry_char, value 16.